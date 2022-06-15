package com.horse.yun.starter.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 公共线程池生产者
 * @date 2022/6/15 15:12
 */
public class CommonThreadPool {
    public static ThreadPoolExecutor getInstance(String threadPoolId) {
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue workQueue = new LinkedBlockingQueue(512);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                5,
                10000,
                unit,
                workQueue,
                r -> {
                    Thread thread = new Thread(r);
                    thread.setDaemon(false);
                    thread.setName(threadPoolId);
                    return thread;
                });
        return poolExecutor;
    }
}
