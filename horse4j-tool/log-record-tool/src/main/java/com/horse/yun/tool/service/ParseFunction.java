package com.horse.yun.tool.service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 函数解析
 * @date 2022/6/21 15:35
 */
public interface ParseFunction {
    /**
     * 是否先执行.
     *
     * @return
     */
    default boolean executeBefore() {
        return false;
    }

    /**
     * 函数名称.
     *
     * @return
     */
    String functionName();

    /**
     * 执行.
     *
     * @param value
     * @return
     */
    String apply(String value);
}
