package com.horse.yun.notify;

import com.horse.yun.event.Event;
import com.horse.yun.notify.listener.Subscriber;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:27
 */
public interface EventPublisher {
    void init(Class<? extends Event> type, int bufferSize);

    void addSubscriber(Subscriber subscriber);

    boolean publish(Event event);

    void notifySubscriber(Subscriber subscriber, Event event);
}
