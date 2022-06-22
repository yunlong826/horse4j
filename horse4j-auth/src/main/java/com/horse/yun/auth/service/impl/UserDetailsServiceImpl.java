package com.horse.yun.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.horse.yun.auth.mapper.UserMapper;
import com.horse.yun.auth.model.UserInfo;
import com.horse.yun.auth.model.biz.user.JwtUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 12:56
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo userInfo = userMapper.selectOne(Wrappers.lambdaQuery(UserInfo.class).eq(UserInfo::getUserName,userName));

        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(userInfo.getId());
        jwtUser.setUsername(userName);
        jwtUser.setPassword(userInfo.getPassword());

        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(userInfo.getRole() + ""));
        jwtUser.setAuthorities(authorities);

        return jwtUser;
    }
}
