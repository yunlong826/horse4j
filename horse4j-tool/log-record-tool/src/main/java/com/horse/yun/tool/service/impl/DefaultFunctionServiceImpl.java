package com.horse.yun.tool.service.impl;

import com.horse.yun.tool.service.FunctionService;
import com.horse.yun.tool.service.ParseFunction;
import lombok.AllArgsConstructor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:37
 */
@AllArgsConstructor
public class DefaultFunctionServiceImpl implements FunctionService {
    private final ParseFunctionFactory parseFunctionFactory;

    @Override
    public String apply(String functionName, String value) {
        ParseFunction function = parseFunctionFactory.getFunction(functionName);
        if (function == null) {
            return value;
        }
        return function.apply(value);
    }

    @Override
    public boolean beforeFunction(String functionName) {
        return parseFunctionFactory.isBeforeFunction(functionName);
    }
}
