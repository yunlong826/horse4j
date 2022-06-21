package com.horse.yun.starter.adapter;

import com.horse.yun.starter.core.ThreadPoolDynamicRefresh;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 15:16
 */
public class ConfigAdapter {
    /**
     * 回调修改线程池配置
     *
     * @param config
     */
    public void callbackConfig(String config) {
        ThreadPoolDynamicRefresh.refreshDynamicPool(config);
    }
}
