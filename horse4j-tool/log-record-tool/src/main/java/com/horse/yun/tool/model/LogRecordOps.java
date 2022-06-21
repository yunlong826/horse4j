package com.horse.yun.tool.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 日志操作记录
 * @date 2022/6/21 15:20
 */
@Data
@Builder
public class LogRecordOps {
    private String successLogTemplate;

    private String failLogTemplate;

    private String operatorId;

    private String bizKey;

    private String bizNo;

    private String category;

    private String detail;

    private String condition;
}
