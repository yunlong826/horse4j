package com.horse.yun.starter.operation;

import com.horse.yun.starter.config.DynamicThreadPoolProperties;
import com.horse.yun.starter.core.ConfigService;
import com.horse.yun.starter.listener.Listener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 15:14
 */
public class ThreadPoolOperation {
    @Autowired
    private ConfigService configService;

    private final DynamicThreadPoolProperties properties;

    public ThreadPoolOperation(DynamicThreadPoolProperties properties) {
        this.properties = properties;
    }

    public Listener subscribeConfig(String tpId, Executor executor, ThreadPoolSubscribeCallback threadPoolSubscribeCallback) {
        Listener configListener = new Listener() {
            @Override
            public void receiveConfigInfo(String config) {
                threadPoolSubscribeCallback.callback(config);
            }

            @Override
            public Executor getExecutor() {
                return executor;
            }
        };

        configService.addListener(properties.getNamespace(), properties.getItemId(), tpId, configListener);

        return configListener;
    }
}
