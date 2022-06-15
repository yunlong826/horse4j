package com.horse.yun.starter.util;

import com.horse.yun.common.enums.QueueTypeEnum;
import com.horse.yun.starter.core.ResizableCapacityLinkedBlockIngQueue;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程修改工具类
 * @date 2022/6/15 15:17
 */
public class ThreadPoolChangeUtil {
    public static void changePool(ThreadPoolExecutor executor, Integer coreSize, Integer maxSize, Integer queueType, Integer capacity, Integer keepAliveTime) {
        if (coreSize != null) {
            executor.setCorePoolSize(coreSize);
        }
        if (maxSize != null) {
            executor.setMaximumPoolSize(maxSize);
        }
        if (capacity != null && Objects.equals(QueueTypeEnum.RESIZABLE_LINKED_BLOCKING_QUEUE.type, queueType)) {
            ResizableCapacityLinkedBlockIngQueue queue = (ResizableCapacityLinkedBlockIngQueue) executor.getQueue();
            queue.setCapacity(capacity);
        }
        if (keepAliveTime != null) {
            executor.setKeepAliveTime(keepAliveTime, TimeUnit.SECONDS);
        }
    }
}
