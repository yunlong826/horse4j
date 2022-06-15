package com.horse.yun.service.biz;

import com.horse.yun.model.ConfigAllInfo;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 服务端配置接口
 * @date 2022/6/15 16:06
 */
public interface ConfigService {
    /**
     * 查询配置全部信息
     *
     * @param tpId      tpId
     * @param itemId    itemId
     * @param namespace namespace
     * @return 全部配置信息
     */
    ConfigAllInfo findConfigAllInfo(String tpId, String itemId, String namespace);

    /**
     * 新增或修改
     *
     * @param configAllInfo
     */
    void insertOrUpdate(ConfigAllInfo configAllInfo);
}
