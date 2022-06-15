package com.horse.yun.notify;


import com.horse.yun.event.Event;
import com.horse.yun.event.SlowEvent;
import com.horse.yun.notify.listener.SmartSubscriber;
import com.horse.yun.notify.listener.Subscriber;
import com.horse.yun.util.ClassUtil;
import com.horse.yun.util.MapUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:27
 */
@Slf4j
public class NotifyCenter {
    private static final NotifyCenter INSTANCE = new NotifyCenter();

    public static int ringBufferSize = 16384;

    public static int shareBufferSize = 1024;

    private DefaultSharePublisher sharePublisher;

    private static Class<? extends EventPublisher> clazz = null;

    private static EventPublisher eventPublisher = new DefaultPublisher();

    private static BiFunction<Class<? extends Event>, Integer, EventPublisher> publisherFactory = null;

    private final Map<String, EventPublisher> publisherMap = new ConcurrentHashMap(16);

    static {
        publisherFactory = (cls, buffer) -> {
            try {
                EventPublisher publisher = eventPublisher;
                publisher.init(cls, buffer);
                return publisher;
            } catch (Throwable ex) {
                log.error("Service class newInstance has error : {}", ex);
                throw new RuntimeException(ex);
            }
        };

        INSTANCE.sharePublisher = new DefaultSharePublisher();
        INSTANCE.sharePublisher.init(SlowEvent.class, shareBufferSize);
    }
    public static void registerSubscriber(final Subscriber consumer) {
        if (consumer instanceof SmartSubscriber) {
            for (Class<? extends Event> subscribeType : ((SmartSubscriber) consumer).subscribeTypes()) {
                if (ClassUtil.isAssignableFrom(SlowEvent.class, subscribeType)) {
                    INSTANCE.sharePublisher.addSubscriber(consumer, subscribeType);
                } else {
                    addSubscriber(consumer, subscribeType);
                }
            }
            return;
        }

        final Class<? extends Event> subscribeType = consumer.subscribeType();
        if (ClassUtil.isAssignableFrom(SlowEvent.class, subscribeType)) {
            INSTANCE.sharePublisher.addSubscriber(consumer, subscribeType);
            return;
        }

        addSubscriber(consumer, subscribeType);
    }
    private static void addSubscriber(final Subscriber consumer, Class<? extends Event> subscribeType) {
        final String topic = ClassUtil.getCanonicalName(subscribeType);
        synchronized (NotifyCenter.class) {
            MapUtil.computeIfAbsent(INSTANCE.publisherMap, topic, publisherFactory, subscribeType, ringBufferSize);
        }
        EventPublisher publisher = INSTANCE.publisherMap.get(topic);
        publisher.addSubscriber(consumer);
    }

    public static EventPublisher registerToPublisher(final Class<? extends Event> eventType, final int queueMaxSize) {
        if (ClassUtil.isAssignableFrom(SlowEvent.class, eventType)) {
            return INSTANCE.sharePublisher;
        }

        final String topic = ClassUtil.getCanonicalName(eventType);
        synchronized (NotifyCenter.class) {
            MapUtil.computeIfAbsent(INSTANCE.publisherMap, topic, publisherFactory, eventType, queueMaxSize);
        }
        return INSTANCE.publisherMap.get(topic);
    }
    public static boolean publishEvent(final Event event) {
        try {
            return publishEvent(event.getClass(), event);
        } catch (Throwable ex) {
            log.error("There was an exception to the message publishing : {}", ex);
            return false;
        }
    }
    private static boolean publishEvent(final Class<? extends Event> eventType, final Event event) {
        if (ClassUtil.isAssignableFrom(SlowEvent.class, eventType)) {
            return INSTANCE.sharePublisher.publish(event);
        }

        final String topic = ClassUtil.getCanonicalName(eventType);

        EventPublisher publisher = INSTANCE.publisherMap.get(topic);
        if (publisher != null) {
            return publisher.publish(event);
        }
        log.warn("There are no [{}] publishers for this event, please register", topic);
        return false;
    }
}
