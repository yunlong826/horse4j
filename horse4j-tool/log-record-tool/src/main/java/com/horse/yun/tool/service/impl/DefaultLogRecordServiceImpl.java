package com.horse.yun.tool.service.impl;

import com.horse.yun.tool.model.LogRecordInfo;
import com.horse.yun.tool.service.LogRecordService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/21 15:38
 */
@Slf4j
public class DefaultLogRecordServiceImpl implements LogRecordService {
    @Override
    public void record(LogRecordInfo logRecordInfo) {
        log.info("Log print :: {}",logRecordInfo);
    }
}
