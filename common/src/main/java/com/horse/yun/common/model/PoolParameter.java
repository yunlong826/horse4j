package com.horse.yun.common.model;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:16
 */
public interface PoolParameter {
    /**
     * namespace
     *
     * @return
     */
    String getNamespace();

    /**
     * itemId
     *
     * @return
     */
    String getItemId();

    /**
     * tpId
     *
     * @return
     */
    String getTpId();

    /**
     * coreSize
     *
     * @return
     */
    Integer getCoreSize();

    /**
     * maxSize
     *
     * @return
     */
    Integer getMaxSize();

    /**
     * queueType
     *
     * @return
     */
    Integer getQueueType();

    /**
     * capacity
     *
     * @return
     */
    Integer getCapacity();

    /**
     * keepAliveTime
     *
     * @return
     */
    Integer getKeepAliveTime();

    /**
     * isAlarm
     *
     * @return
     */
    Integer getIsAlarm();

    /**
     * capacityAlarm
     *
     * @return
     */
    Integer getCapacityAlarm();

    /**
     * livenessAlarm
     *
     * @return
     */
    Integer getLivenessAlarm();
}
