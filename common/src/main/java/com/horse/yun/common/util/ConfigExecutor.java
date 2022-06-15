package com.horse.yun.common.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.horse.yun.common.executor.ExecutorFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:20
 */
public class ConfigExecutor {
    private static final ScheduledExecutorService LONG_POLLING_EXECUTOR = ExecutorFactory.Managed
            .newSingleScheduledExecutorService("default group",
                    ThreadFactoryBuilder.create()
                            .setNamePrefix("io.dynamic.threadPool.config.LongPolling")
                            .build());

    public static void executeLongPolling(Runnable runnable) {
        LONG_POLLING_EXECUTOR.execute(runnable);
    }

    public static ScheduledFuture<?> scheduleLongPolling(Runnable runnable, long period, TimeUnit unit) {
        return LONG_POLLING_EXECUTOR.schedule(runnable, period, unit);
    }

    public static void scheduleLongPolling(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        LONG_POLLING_EXECUTOR.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
    }
}
