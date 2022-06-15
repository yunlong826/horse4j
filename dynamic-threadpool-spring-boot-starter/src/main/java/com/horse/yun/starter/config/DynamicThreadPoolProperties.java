package com.horse.yun.starter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 动态线程池配置
 * @date 2022/6/15 14:05
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicThreadPoolProperties.PREFIX)
public class DynamicThreadPoolProperties {
    public static final String PREFIX = "spring.dynamic.thread-pool";

    /**
     * 服务地址
     */
    private String serverAddr;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 项目 Id
     */
    private String itemId;

    /**
     * 是否开启动态线程池
     */
    private String enabled;
}
