package com.horse.yun.notify.listener;

import com.horse.yun.event.Event;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:36
 */
public abstract class SmartSubscriber extends Subscriber{
    public abstract List<Class<? extends Event>> subscribeTypes();
}
