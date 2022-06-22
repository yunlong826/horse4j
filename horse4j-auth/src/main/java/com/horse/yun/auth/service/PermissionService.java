package com.horse.yun.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.auth.model.biz.permission.PermissionRespDTO;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:25
 */
public interface PermissionService {
    /**
     * 分页查询权限列表.
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<PermissionRespDTO> listPermission(int pageNo, int pageSize);

    /**
     * 新增权限.
     *
     * @param role
     * @param resource
     * @param action
     */
    void addPermission(String role, String resource, String action);

    /**
     * 删除权限.
     *
     * @param role
     * @param resource
     * @param action
     */
    void deletePermission(String role, String resource, String action);
}
