package com.horse.yun.common.util;

import com.alibaba.fastjson.JSON;
import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.model.PoolParameter;
import com.horse.yun.common.model.PoolParameterInfo;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:16
 */
public class ContentUtil {
    public static String getPoolContent(PoolParameter parameter) {
        PoolParameterInfo poolInfo = new PoolParameterInfo();
        poolInfo.setNamespace(parameter.getNamespace());
        poolInfo.setItemId(parameter.getItemId());
        poolInfo.setTpId(parameter.getTpId());
        poolInfo.setCoreSize(parameter.getCoreSize());
        poolInfo.setMaxSize(parameter.getMaxSize());
        poolInfo.setQueueType(parameter.getQueueType());
        poolInfo.setCapacity(parameter.getCapacity());
        poolInfo.setKeepAliveTime(parameter.getKeepAliveTime());
        poolInfo.setIsAlarm(parameter.getIsAlarm());
        poolInfo.setCapacityAlarm(parameter.getCapacityAlarm());
        poolInfo.setLivenessAlarm(parameter.getLivenessAlarm());
        return JSON.toJSONString(poolInfo);
    }

    public static String getGroupKey(PoolParameter parameter) {
        StringBuilder stringBuilder = new StringBuilder();
        String resultStr = stringBuilder.append(parameter.getTpId())
                .append(Constants.GROUP_KEY_DELIMITER)
                .append(parameter.getItemId())
                .append(Constants.GROUP_KEY_DELIMITER)
                .append(parameter.getNamespace())
                .toString();
        return resultStr;
    }
}
