package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.service.biz.tenant.TenantQueryReqDTO;
import com.horse.yun.service.biz.tenant.TenantRespDTO;
import com.horse.yun.service.biz.tenant.TenantSaveReqDTO;
import com.horse.yun.service.biz.tenant.TenantUpdateReqDTO;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:57
 */
public interface TenantService {
    /**
     * 根据 Id 获取业务线
     *
     * @param namespaceId
     * @return
     */
    TenantRespDTO getNameSpaceById(String namespaceId);

    /**
     * 分页查询业务线
     *
     * @param reqDTO
     * @return
     */
    IPage<TenantRespDTO> queryNameSpacePage(TenantQueryReqDTO reqDTO);

    /**
     * 新增业务线
     *
     * @param reqDTO
     */
    void saveNameSpace(TenantSaveReqDTO reqDTO);

    /**
     * 修改业务线
     *
     * @param reqDTO
     */
    void updateNameSpace(TenantUpdateReqDTO reqDTO);

    /**
     * 根据 Id 删除业务线
     *
     * @param namespaceId
     */
    void deleteNameSpaceById(String namespaceId);
}
