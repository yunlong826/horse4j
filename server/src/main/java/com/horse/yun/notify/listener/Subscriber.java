package com.horse.yun.notify.listener;

import com.horse.yun.event.Event;

import java.util.concurrent.Executor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:29
 */
public abstract class Subscriber<T extends Event> {
    /**
     * Event callback.
     *
     * @param event
     */
    public abstract void onEvent(T event);

    /**
     * Type of this subscriber's subscription.
     *
     * @return
     */
    public abstract Class<? extends Event> subscribeType();

    public Executor executor() {
        return null;
    }
}
