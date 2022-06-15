package com.horse.yun.starter.listener;

import java.util.concurrent.Executor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 监听器
 * @date 2022/6/15 14:08
 */
public interface Listener {
    /**
     * 获取执行器
     *
     * @return
     */
    Executor getExecutor();

    /**
     * 接受配置信息
     *
     * @param configInfo
     */
    void receiveConfigInfo(String configInfo);
}
