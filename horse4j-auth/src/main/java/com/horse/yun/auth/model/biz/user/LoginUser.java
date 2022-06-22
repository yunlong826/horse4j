package com.horse.yun.auth.model.biz.user;

import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:07
 */
@Data
public class LoginUser {
    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * rememberMe
     */
    private Integer rememberMe;
}
