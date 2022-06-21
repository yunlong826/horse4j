package com.horse.yun.tool.service.impl;

import com.horse.yun.tool.model.Operator;
import com.horse.yun.tool.service.OperatorGetService;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:38
 */
public class DefaultOperatorGetServiceImpl implements OperatorGetService {
    @Override
    public Operator getUser() {
        return new Operator("-");
    }
}
