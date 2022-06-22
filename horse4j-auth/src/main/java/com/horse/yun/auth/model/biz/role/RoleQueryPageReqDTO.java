package com.horse.yun.auth.model.biz.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class RoleQueryPageReqDTO extends Page {

    public RoleQueryPageReqDTO(long current, long size) {
        super(current, size);
    }

}
