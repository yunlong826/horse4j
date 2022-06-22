package com.horse.yun.auth.model.biz.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


@Data
public class UserReqDTO extends Page {

    /**
     * userName
     */
    private String userName;

    /**
     * password
     */
    private String password;

    /**
     * role
     */
    private String role;

}
