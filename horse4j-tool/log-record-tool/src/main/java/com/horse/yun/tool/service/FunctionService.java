package com.horse.yun.tool.service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 函数服务
 * @date 2022/6/21 15:24
 */
public interface FunctionService {
    /**
     * 执行.
     *
     * @param functionName
     * @param value
     * @return
     */
    String apply(String functionName, String value);

    /**
     * 是否提前执行.
     *
     * @param functionName
     * @return
     */
    boolean beforeFunction(String functionName);
}
