package com.horse.yun.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO  公共配置
 * @date 2022/6/15 13:45
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public ApplicationContextHolder applicationContextHolder(){
        return new ApplicationContextHolder();
    }
}
