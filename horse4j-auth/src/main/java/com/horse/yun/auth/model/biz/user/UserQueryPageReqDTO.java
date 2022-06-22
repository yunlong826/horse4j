package com.horse.yun.auth.model.biz.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


@Data
public class UserQueryPageReqDTO extends Page {

    /**
     * userName
     */
    private String userName;

}
