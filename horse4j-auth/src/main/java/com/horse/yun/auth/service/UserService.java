package com.horse.yun.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.auth.model.biz.user.UserQueryPageReqDTO;
import com.horse.yun.auth.model.biz.user.UserReqDTO;
import com.horse.yun.auth.model.biz.user.UserRespDTO;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:22
 */
public interface UserService {
    /**
     * 分页查询用户列表.
     *
     * @param reqDTO
     * @return
     */
    IPage<UserRespDTO> listUser(UserQueryPageReqDTO reqDTO);

    /**
     * 新增用户.
     *
     * @param reqDTO
     */
    void addUser(UserReqDTO reqDTO);

    /**
     * 修改用户.
     *
     * @param reqDTO
     */
    void updateUser(UserReqDTO reqDTO);

    /**
     * 删除用户.
     *
     * @param userName
     */
    void deleteUser(String userName);

    /**
     * 根据用户名模糊搜索.
     *
     * @param userName
     * @return
     */
    List<String> getUserLikeUsername(String userName);
}
