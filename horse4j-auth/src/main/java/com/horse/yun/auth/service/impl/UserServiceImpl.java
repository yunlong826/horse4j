package com.horse.yun.auth.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.horse.yun.auth.mapper.UserMapper;
import com.horse.yun.auth.model.UserInfo;
import com.horse.yun.auth.model.biz.user.UserQueryPageReqDTO;
import com.horse.yun.auth.model.biz.user.UserReqDTO;
import com.horse.yun.auth.model.biz.user.UserRespDTO;
import com.horse.yun.auth.service.RoleService;
import com.horse.yun.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author long_yun
 * @date 2022/6/22 13:27
 * @describe
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public IPage<UserRespDTO> listUser(UserQueryPageReqDTO reqDTO) {
        IPage<UserInfo> selectPage = userMapper.selectPage(reqDTO, null);

        return selectPage.convert(each -> BeanUtil.toBean(each, UserRespDTO.class));
    }

    @Override
    public void addUser(UserReqDTO reqDTO) {
        LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery(UserInfo.class)
                .eq(UserInfo::getUserName, reqDTO.getUserName());
        UserInfo existUserInfo = userMapper.selectOne(queryWrapper);
        if (existUserInfo != null) {
            throw new RuntimeException("用户名重复");
        }

        reqDTO.setPassword(bCryptPasswordEncoder.encode(reqDTO.getPassword()));
        UserInfo insertUser = BeanUtil.toBean(reqDTO, UserInfo.class);
        userMapper.insert(insertUser);
    }

    @Override
    public void updateUser(UserReqDTO reqDTO) {
        if (StrUtil.isNotBlank(reqDTO.getPassword())) {
            reqDTO.setPassword(bCryptPasswordEncoder.encode(reqDTO.getPassword()));
        }
        UserInfo updateUser = BeanUtil.toBean(reqDTO, UserInfo.class);

        LambdaUpdateWrapper<UserInfo> updateWrapper = Wrappers.lambdaUpdate(UserInfo.class)
                .eq(UserInfo::getUserName, reqDTO.getUserName());
        userMapper.update(updateUser, updateWrapper);
    }

    @Override
    public void deleteUser(String userName) {
        LambdaUpdateWrapper<UserInfo> updateWrapper = Wrappers.lambdaUpdate(UserInfo.class)
                .eq(UserInfo::getUserName, userName);
        userMapper.delete(updateWrapper);
        // roleService.deleteRole("", userName);
    }

    @Override
    public List<String> getUserLikeUsername(String userName) {
        LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery(UserInfo.class)
                .like(UserInfo::getUserName, userName)
                .select(UserInfo::getUserName);

        List<UserInfo> userInfos = userMapper.selectList(queryWrapper);
        List<String> userNames = userInfos.stream().map(UserInfo::getUserName).collect(Collectors.toList());

        return userNames;
    }

}
