package com.horse.yun.starter.core;

import com.horse.yun.starter.listener.Listener;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 配置服务类
 * @date 2022/6/15 14:07
 */
public interface ConfigService {
    /**
     * 添加监听器, 如果服务端发生变更, 客户端会使用监听器进行回调
     *
     * @param namespace
     * @param itemId
     * @param tpId
     * @param listener
     */
    void addListener(String namespace, String itemId, String tpId, Listener listener);

    /**
     * 获取服务状态
     *
     * @return
     */
    String getServerStatus();
}
