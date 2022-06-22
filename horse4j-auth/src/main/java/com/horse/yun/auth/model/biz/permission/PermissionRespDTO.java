package com.horse.yun.auth.model.biz.permission;

import lombok.Data;


@Data
public class PermissionRespDTO {

    /**
     * role
     */
    private String role;

    /**
     * source
     */
    private String resource;

    /**
     * action
     */
    private String action;

}
