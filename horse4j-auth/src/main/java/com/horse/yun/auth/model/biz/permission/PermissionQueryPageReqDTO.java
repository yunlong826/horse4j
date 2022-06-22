package com.horse.yun.auth.model.biz.permission;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


@Data
public class PermissionQueryPageReqDTO extends Page {

    public PermissionQueryPageReqDTO(long current, long size) {
        super(current, size);
    }

}
