package com.horse.yun.starter.core;

import com.horse.yun.common.model.PoolParameter;
import com.horse.yun.starter.wrap.DynamicThreadPoolWrap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程池全局管理
 * @date 2022/6/15 14:20
 */
public class GlobalThreadPoolManage {
    private static final Map<String, PoolParameter> POOL_PARAMETER = new ConcurrentHashMap();

    private static final Map<String, DynamicThreadPoolWrap> EXECUTOR_MAP = new ConcurrentHashMap();

    public static DynamicThreadPoolWrap getExecutorService(String tpId) {
        return EXECUTOR_MAP.get(tpId);
    }

    public static PoolParameter getPoolParameter(String tpId) {
        return POOL_PARAMETER.get(tpId);
    }

    public static void register(String tpId, PoolParameter poolParameter, DynamicThreadPoolWrap executor) {
        registerPool(tpId, executor);
        registerPoolParameter(tpId, poolParameter);
    }

    public static void registerPool(String tpId, DynamicThreadPoolWrap executor) {
        EXECUTOR_MAP.put(tpId, executor);
    }

    public static void registerPoolParameter(String tpId, PoolParameter poolParameter) {
        POOL_PARAMETER.put(tpId, poolParameter);
    }
}
