package com.horse.yun.example.config;

import com.horse.yun.starter.wrap.DynamicThreadPoolWrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 17:46
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public DynamicThreadPoolWrap messageCenterConsumeThreadPool() {
        return new DynamicThreadPoolWrap("message-consume");
    }

    @Bean
    public DynamicThreadPoolWrap messageCenterProduceThreadPool() {
        return new DynamicThreadPoolWrap("message-produce");
    }
}
