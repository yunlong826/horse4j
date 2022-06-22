package com.horse.yun.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.auth.model.biz.role.RoleRespDTO;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 13:24
 */
public interface RoleService {
    /**
     * 分页查询角色列表.
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<RoleRespDTO> listRole(int pageNo, int pageSize);

    /**
     * 新增角色.
     *
     * @param role
     * @param userName
     */
    void addRole(String role, String userName);

    /**
     * 删除角色.
     *
     * @param role
     * @param userName
     */
    void deleteRole(String role, String userName);

    /**
     * 根据角色模糊搜索.
     *
     * @param role
     * @return
     */
    List<String> getRoleLike(String role);
}
