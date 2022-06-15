package com.horse.yun.starter.core;

import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.util.ContentUtil;
import com.horse.yun.common.util.Md5Util;
import com.horse.yun.starter.listener.Listener;
import com.horse.yun.starter.wrap.ManagerListenerWrap;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:13
 */
@Slf4j
public class CacheData {
    public volatile String md5;

    public volatile String content;

    public final String namespace;

    public final String itemId;

    public final String tpId;

    private int taskId;

    private volatile long localConfigLastModified;

    private final CopyOnWriteArrayList<ManagerListenerWrap> listeners;

    public CacheData(String namespace, String itemId, String tpId) {
        this.namespace = namespace;
        this.itemId = itemId;
        this.tpId = tpId;
        this.content = ContentUtil.getPoolContent(GlobalThreadPoolManage.getPoolParameter(tpId));
        this.md5 = getMd5String(content);
        this.listeners = new CopyOnWriteArrayList();

    }
    public static String getMd5String(String config) {
        return (null == config) ? Constants.NULL : Md5Util.md5Hex(config, Constants.ENCODE);
    }

    public String getMd5() {
        return this.md5;
    }
    public void setContent(String content) {
        this.content = content;
        this.md5 = getMd5String(this.content);
    }
    public void checkListenerMd5() {
        for (ManagerListenerWrap wrap : listeners) {
            if (!md5.equals(wrap.getLastCallMd5())) {
                safeNotifyListener(content, md5, wrap);
            }
        }
    }
    private void safeNotifyListener(String content, String md5, ManagerListenerWrap wrap) {
        Listener listener = wrap.getListener();

        Runnable runnable = () -> {
            wrap.setLastCallMd5(md5);

            listener.receiveConfigInfo(content);
        };

        listener.getExecutor().execute(runnable);
    }
    public void addListener(Listener listener) {
        if (null == listener) {
            throw new IllegalArgumentException("listener is null");
        }

        ManagerListenerWrap managerListenerWrap = new ManagerListenerWrap(md5, listener);

        if (listeners.addIfAbsent(managerListenerWrap)) {
            log.info("[add-listener] ok, tpId :: {}, cnt :: {}", tpId, listeners.size());
        }
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
