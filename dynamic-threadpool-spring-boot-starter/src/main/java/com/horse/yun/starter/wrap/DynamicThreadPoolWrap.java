package com.horse.yun.starter.wrap;

import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程池包装
 * @date 2022/6/15 14:20
 */
@Data
public class DynamicThreadPoolWrap {
    private String namespace;

    private String itemId;

    private String tpId;

    private ThreadPoolExecutor pool;

    /**
     * 首选服务端线程池, 为空使用默认线程池 {@link CommonThreadPool#getInstance(String)}
     *
     * @param threadPoolId
     */
    public DynamicThreadPoolWrap(String threadPoolId) {
        this(threadPoolId, null);
    }

    /**
     * 首选服务端线程池, 为空使用 threadPoolExecutor
     *
     * @param threadPoolId
     * @param threadPoolExecutor
     */
    public DynamicThreadPoolWrap(String threadPoolId, ThreadPoolExecutor threadPoolExecutor) {
        this.tpId = threadPoolId;
        this.pool = threadPoolExecutor;
    }

    /**
     * 提交任务
     *
     * @param command
     */
    public void execute(Runnable command) {
        pool.execute(command);
    }

    /**
     * 提交任务
     *
     * @param task
     * @return
     */
    public Future<?> submit(Runnable task) {
        return pool.submit(task);
    }

    /**
     * 提交任务
     *
     * @param task
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(Callable<T> task) {
        return pool.submit(task);
    }
}
