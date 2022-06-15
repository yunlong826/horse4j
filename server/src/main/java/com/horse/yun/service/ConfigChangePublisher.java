package com.horse.yun.service;

import com.horse.yun.event.LocalDataChangeEvent;
import com.horse.yun.notify.NotifyCenter;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:44
 */
public class ConfigChangePublisher {
    /**
     * Notify ConfigChange.
     *
     * @param event
     */
    public static void notifyConfigChange(LocalDataChangeEvent event) {
        NotifyCenter.publishEvent(event);
    }
}
