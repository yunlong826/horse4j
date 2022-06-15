package com.horse.yun.common.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:21
 */
public class ExecutorFactory {
    public static final class Managed {

        private static final String DEFAULT_NAMESPACE = "dynamic.thread-pool";

        private static final ThreadPoolManager THREAD_POOL_MANAGER = ThreadPoolManager.getInstance();

        public static ScheduledExecutorService newSingleScheduledExecutorService(String group, ThreadFactory threadFactory) {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, threadFactory);
            THREAD_POOL_MANAGER.register(DEFAULT_NAMESPACE, group, executorService);
            return executorService;
        }
    }
}
