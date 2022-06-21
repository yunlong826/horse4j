package com.horse.yun.tool.config;

import com.horse.yun.tool.parse.LogRecordOperationSource;
import com.horse.yun.tool.parse.LogRecordValueParser;
import com.horse.yun.tool.service.FunctionService;
import com.horse.yun.tool.service.LogRecordService;
import com.horse.yun.tool.service.OperatorGetService;
import com.horse.yun.tool.service.ParseFunction;
import com.horse.yun.tool.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 日志记录配置
 * @date 2022/6/21 15:34
 */
@Configuration
public class LogRecordConfig {
    @Bean
    @ConditionalOnMissingBean(ParseFunction.class)
    public DefaultParseFunction parseFunction() {
        return new DefaultParseFunction();
    }


    @Bean
    public ParseFunctionFactory parseFunctionFactory(@Autowired List<ParseFunction> parseFunctions) {
        return new ParseFunctionFactory(parseFunctions);
    }

    @Bean
    @ConditionalOnMissingBean(FunctionService.class)
    public FunctionService functionService(ParseFunctionFactory parseFunctionFactory) {
        return new DefaultFunctionServiceImpl(parseFunctionFactory);
    }

    @Bean
    @Role(BeanDefinition.ROLE_APPLICATION)
    @ConditionalOnMissingBean(OperatorGetService.class)
    public OperatorGetService operatorGetService() {
        return new DefaultOperatorGetServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(LogRecordService.class)
    @Role(BeanDefinition.ROLE_APPLICATION)
    public LogRecordService recordService() {
        return new DefaultLogRecordServiceImpl();
    }

    @Bean
    public LogRecordValueParser logRecordValueParser() {
        return new LogRecordValueParser();
    }

    @Bean
    public LogRecordOperationSource logRecordOperationSource() {
        return new LogRecordOperationSource();
    }
}
