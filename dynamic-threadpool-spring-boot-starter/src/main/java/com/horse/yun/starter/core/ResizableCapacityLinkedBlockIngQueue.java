package com.horse.yun.starter.core;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 15:11
 */
@Slf4j
public class ResizableCapacityLinkedBlockIngQueue extends LinkedBlockingQueue {
    public ResizableCapacityLinkedBlockIngQueue(int capacity) {
        super(capacity);
    }
    public boolean setCapacity(Integer capacity) {
        boolean successFlag = true;
        try {
            ReflectUtil.setFieldValue(this, "capacity", capacity);
        } catch (Exception ex) {
            // ignore
            log.error("动态修改阻塞队列大小失败.", ex);
            successFlag = false;
        }
        return successFlag;
    }
}
