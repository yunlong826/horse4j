package com.horse.yun.starter.core;

import com.horse.yun.starter.config.DynamicThreadPoolProperties;
import com.horse.yun.starter.listener.ClientWorker;
import com.horse.yun.starter.listener.Listener;
import com.horse.yun.starter.remote.HttpAgent;
import com.horse.yun.starter.remote.ServerHttpAgent;

import java.util.Arrays;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程配置服务类
 * @date 2022/6/15 14:09
 */
public class ThreadPoolConfigService implements ConfigService{

    private final HttpAgent httpAgent;

    private final ClientWorker clientWorker;

    public ThreadPoolConfigService(DynamicThreadPoolProperties properties) {
        httpAgent = new ServerHttpAgent(properties);
        clientWorker = new ClientWorker(httpAgent);
    }
    @Override
    public void addListener(String namespace, String itemId, String tpId, Listener listener) {
        clientWorker.addTenantListeners(namespace, itemId, tpId, Arrays.asList(listener));
    }

    @Override
    public String getServerStatus() {
        if (clientWorker.isHealthServer()) {
            return "UP";
        } else {
            return "DOWN";
        }
    }
}
