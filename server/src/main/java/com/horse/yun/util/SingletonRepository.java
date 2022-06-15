package com.horse.yun.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:15
 */
public class SingletonRepository<T>{
    public SingletonRepository() {
        shared = new ConcurrentHashMap(1 << 16);
    }

    public T getSingleton(T obj) {
        T previous = shared.putIfAbsent(obj, obj);
        return (null == previous) ? obj : previous;
    }

    public int size() {
        return shared.size();
    }

    public void remove(Object obj) {
        shared.remove(obj);
    }

    private final ConcurrentHashMap<T, T> shared;

    public static class DataIdGroupIdCache {

        public static String getSingleton(String str) {
            return cache.getSingleton(str);
        }

        static SingletonRepository<String> cache = new SingletonRepository<String>();
    }
}
