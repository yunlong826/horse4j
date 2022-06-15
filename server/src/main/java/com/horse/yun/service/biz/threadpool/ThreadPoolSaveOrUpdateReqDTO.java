package com.horse.yun.service.biz.threadpool;

import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:54
 */
@Data
public class ThreadPoolSaveOrUpdateReqDTO {
    /**
     * namespace
     */
    private String namespace;

    /**
     * TpId
     */
    private String tpId;

    /**
     * ItemId
     */
    private String itemId;

    /**
     * 核心线程数
     */
    private Integer coreSize;

    /**
     * 最大线程数
     */
    private Integer maxSize;

    /**
     * 队列类型
     */
    private Integer queueType;

    /**
     * 队列长度
     */
    private Integer capacity;

    /**
     * 线程存活时长
     */
    private Integer keepAliveTime;

    /**
     * 是否告警
     */
    private Integer isAlarm;

    /**
     * 容量告警
     */
    private Integer capacityAlarm;

    /**
     * 活跃度告警
     */
    private Integer livenessAlarm;
}
