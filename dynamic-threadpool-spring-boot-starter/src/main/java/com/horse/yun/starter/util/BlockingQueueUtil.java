package com.horse.yun.starter.util;

import com.horse.yun.common.enums.QueueTypeEnum;
import com.horse.yun.starter.core.ResizableCapacityLinkedBlockIngQueue;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 阻塞队列工具类
 * @date 2022/6/15 15:09
 */
public class BlockingQueueUtil {
    public static BlockingQueue createBlockingQueue(Integer type, Integer capacity) {
        BlockingQueue blockingQueue = null;
        if (Objects.equals(type, QueueTypeEnum.ARRAY_BLOCKING_QUEUE.type)) {
            blockingQueue = new ArrayBlockingQueue(capacity);
        } else if (Objects.equals(type, QueueTypeEnum.LINKED_BLOCKING_QUEUE.type)) {
            blockingQueue = new LinkedBlockingQueue(capacity);
        } else if (Objects.equals(type, QueueTypeEnum.LINKED_BLOCKING_DEQUE.type)) {
            blockingQueue = new LinkedBlockingDeque(capacity);
        } else if (Objects.equals(type, QueueTypeEnum.SYNCHRONOUS_QUEUE.type)) {
            blockingQueue = new SynchronousQueue();
        } else if (Objects.equals(type, QueueTypeEnum.LINKED_TRANSFER_QUEUE.type)) {
            blockingQueue = new LinkedTransferQueue();
        } else if (Objects.equals(type, QueueTypeEnum.PRIORITY_BLOCKING_QUEUE.type)) {
            blockingQueue = new PriorityBlockingQueue(capacity);
        } else if (Objects.equals(type, QueueTypeEnum.RESIZABLE_LINKED_BLOCKING_QUEUE.type)) {
            blockingQueue = new ResizableCapacityLinkedBlockIngQueue(capacity);
        } else {
            throw new IllegalArgumentException("未找到类型匹配的阻塞队列.");
        }
        return blockingQueue;
    }
}
