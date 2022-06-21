package com.horse.yun.tool.service.impl;

import com.horse.yun.tool.service.ParseFunction;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:35
 */
public class DefaultParseFunction implements ParseFunction {
    @Override
    public String functionName() {
        return null;
    }

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String apply(String value) {
        return null;
    }
}
