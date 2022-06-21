package com.horse.yun.starter.wrap;

import com.horse.yun.starter.listener.Listener;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 监听包装
 * @date 2022/6/15 14:15
 */
@Getter
@Setter
public class ManagerListenerWrap {
    final Listener listener;

    String lastCallMd5;

    public ManagerListenerWrap(String md5, Listener listener) {
        this.lastCallMd5 = md5;
        this.listener = listener;
    }
}
