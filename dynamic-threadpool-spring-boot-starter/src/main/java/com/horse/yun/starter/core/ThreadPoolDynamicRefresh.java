package com.horse.yun.starter.core;

import com.alibaba.fastjson.JSON;
import com.horse.yun.common.model.PoolParameterInfo;
import com.horse.yun.starter.util.ThreadPoolChangeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程池动态刷新
 * @date 2022/6/15 15:17
 */
@Slf4j
public class ThreadPoolDynamicRefresh {
    public static void refreshDynamicPool(String content) {
        PoolParameterInfo parameter = JSON.parseObject(content, PoolParameterInfo.class);
        String tpId = parameter.getTpId();
        Integer coreSize, maxSize, queueType, capacity, keepAliveTime;
        log.info("[🔥] Start refreshing configuration. tpId :: {}, coreSize :: {}, maxSize :: {}, queueType :: {}, capacity :: {}, keepAliveTime :: {}",
                tpId, coreSize = parameter.getCoreSize(), maxSize = parameter.getMaxSize(),
                queueType = parameter.getQueueType(), capacity = parameter.getCapacity(), keepAliveTime = parameter.getKeepAliveTime());
        refreshDynamicPool(tpId, coreSize, maxSize, queueType, capacity, keepAliveTime);
    }

    public static void refreshDynamicPool(String threadPoolId, Integer coreSize, Integer maxSize, Integer queueType, Integer capacity, Integer keepAliveTime) {
        ThreadPoolExecutor executor = GlobalThreadPoolManage.getExecutorService(threadPoolId).getPool();
        log.info("[✈️] Original thread pool. coreSize :: {}, maxSize :: {}, queueType :: {}, capacity :: {}, keepAliveTime :: {}",
                executor.getCorePoolSize(), executor.getMaximumPoolSize(), queueType, executor.getQueue().remainingCapacity(), executor.getKeepAliveTime(TimeUnit.MILLISECONDS));

        ThreadPoolChangeUtil.changePool(executor, coreSize, maxSize, queueType, capacity, keepAliveTime);
        ThreadPoolExecutor afterExecutor = GlobalThreadPoolManage.getExecutorService(threadPoolId).getPool();
        log.info("[🚀] Changed thread pool. coreSize :: {}, maxSize :: {}, queueType :: {}, capacity :: {}, keepAliveTime :: {}",
                afterExecutor.getCorePoolSize(), afterExecutor.getMaximumPoolSize(), queueType, afterExecutor.getQueue().remainingCapacity(), afterExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS));
    }
}
