package com.horse.yun.auth.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 12:58
 */
@Data
@TableName("user")
public class UserInfo {
    /**
     * id
     */
    @TableId
    private Long id;

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

    /**
     * gmtCreate
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * gmtModified
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * delFlag
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
