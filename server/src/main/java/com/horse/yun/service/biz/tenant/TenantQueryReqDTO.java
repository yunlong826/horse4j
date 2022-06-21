package com.horse.yun.service.biz.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


@Data
public class TenantQueryReqDTO extends Page {

    private String tenantId;

    private String tenantName;

    private String owner;
}
