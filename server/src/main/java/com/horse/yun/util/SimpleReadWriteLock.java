package com.horse.yun.util;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 简单读写锁
 * @date 2022/6/15 16:14
 */
public class SimpleReadWriteLock {
    public synchronized boolean tryReadLock() {
        if (isWriteLocked()) {
            return false;
        } else {
            status++;
            return true;
        }
    }

    public synchronized void releaseReadLock() {
        status--;
    }

    public synchronized boolean tryWriteLock() {
        if (!isFree()) {
            return false;
        } else {
            status = -1;
            return true;
        }
    }

    public synchronized void releaseWriteLock() {
        status = 0;
    }

    private boolean isWriteLocked() {
        return status < 0;
    }

    private boolean isFree() {
        return status == 0;
    }

    private int status = 0;
}
