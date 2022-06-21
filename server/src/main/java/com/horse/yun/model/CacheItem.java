package com.horse.yun.model;

import com.horse.yun.common.constant.Constants;
import com.horse.yun.util.SimpleReadWriteLock;
import com.horse.yun.util.SingletonRepository;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:14
 */
@Getter
@Setter
public class CacheItem {
    final String groupKey;

    public volatile String md5 = Constants.NULL;

    public volatile long lastModifiedTs;

    public SimpleReadWriteLock rwLock = new SimpleReadWriteLock();

    public CacheItem(String groupKey) {
        this.groupKey = SingletonRepository.DataIdGroupIdCache.getSingleton(groupKey);
    }

    public CacheItem(String groupKey, String md5) {
        this.md5 = md5;
        this.groupKey = SingletonRepository.DataIdGroupIdCache.getSingleton(groupKey);
    }
}
