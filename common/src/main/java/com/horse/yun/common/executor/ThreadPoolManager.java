package com.horse.yun.common.executor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:22
 */
public class ThreadPoolManager {
    private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();
    private Map<String, Object> lockers = new ConcurrentHashMap(8);
    private Map<String, Map<String, Set<ExecutorService>>> resourcesManager;
    public static ThreadPoolManager getInstance() {
        return INSTANCE;
    }
    static {
        INSTANCE.init();
    }
    private void init() {
        resourcesManager = new ConcurrentHashMap<String, Map<String, Set<ExecutorService>>>(8);
    }

    public void register(String namespace, String group, ScheduledExecutorService executor) {
        if (!resourcesManager.containsKey(namespace)) {
            synchronized (this) {
                lockers.put(namespace, new Object());
            }
        }

        final Object monitor = lockers.get(namespace);
        synchronized (monitor) {
            Map<String, Set<ExecutorService>> map = resourcesManager.get(namespace);
            if (map == null) {
                map = new HashMap(8);
                map.put(group, new HashSet());
                map.get(group).add(executor);
                resourcesManager.put(namespace, map);
                return;
            }
            if (!map.containsKey(group)) {
                map.put(group, new HashSet());
            }
            map.get(group).add(executor);
        }
    }
}
