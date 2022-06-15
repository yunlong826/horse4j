package com.horse.yun.starter.config;

import com.horse.yun.starter.adapter.ThreadPoolConfigAdapter;
import com.horse.yun.starter.core.ConfigService;
import com.horse.yun.starter.core.ThreadPoolConfigService;
import com.horse.yun.starter.listener.ThreadPoolRunListener;
import com.horse.yun.starter.operation.ThreadPoolOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 动态线程池自动装配类
 * @date 2022/6/15 14:04
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(DynamicThreadPoolProperties.class)
@ConditionalOnProperty(prefix = DynamicThreadPoolProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicThreadPoolAutoConfiguration {
    private final DynamicThreadPoolProperties properties;

    @Bean
    public ConfigService configService() {
        return new ThreadPoolConfigService(properties);
    }

    @Bean
    public ThreadPoolRunListener threadPoolRunListener() {
        return new ThreadPoolRunListener(properties);
    }

    @Bean
    public ThreadPoolConfigAdapter threadPoolConfigAdapter() {
        return new ThreadPoolConfigAdapter();
    }

    @Bean
    public ThreadPoolOperation threadPoolOperation() {
        return new ThreadPoolOperation(properties);
    }
}
