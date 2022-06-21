package com.horse.yun.tool.service;

import com.horse.yun.tool.model.LogRecordInfo;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:33
 */
public interface LogRecordService {
    /**
     * 保存日志.
     *
     * @param logRecordInfo
     */
    void record(LogRecordInfo logRecordInfo);
}
