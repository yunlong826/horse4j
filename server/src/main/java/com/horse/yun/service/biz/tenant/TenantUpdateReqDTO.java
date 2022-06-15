package com.horse.yun.service.biz.tenant;

import lombok.Data;

/**
 * Tenant Save Req DTO.
 *
 * @author chen.ma
 * @date 2021/6/29 20:40
 */
@Data
public class TenantUpdateReqDTO {

    /**
     * 租户 ID
     */
    private String namespaceId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 租户简介
     */
    private String tenantDesc;

    /**
     * 负责人
     */
    private String owner;

}
