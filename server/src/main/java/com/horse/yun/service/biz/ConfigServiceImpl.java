package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.horse.yun.common.util.ContentUtil;
import com.horse.yun.common.util.Md5Util;
import com.horse.yun.event.LocalDataChangeEvent;
import com.horse.yun.mapper.ConfigInfoMapper;
import com.horse.yun.model.ConfigAllInfo;
import com.horse.yun.service.ConfigChangePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:42
 */
@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService{
    @Resource
    private ConfigInfoMapper configInfoMapper;
    @Override
    public ConfigAllInfo findConfigAllInfo(String tpId, String itemId, String namespace) {
        LambdaQueryWrapper<ConfigAllInfo> wrapper = Wrappers.lambdaQuery(ConfigAllInfo.class)
                .eq(!StringUtils.isBlank(tpId), ConfigAllInfo::getTpId, tpId)
                .eq(!StringUtils.isBlank(itemId), ConfigAllInfo::getItemId, itemId)
                .eq(!StringUtils.isBlank(namespace), ConfigAllInfo::getNamespace, namespace);
        ConfigAllInfo configAllInfo = configInfoMapper.selectOne(wrapper);
        return configAllInfo;
    }

    @Override
    public void insertOrUpdate(ConfigAllInfo configAllInfo) {
        try {
            addConfigInfo(configAllInfo);
        } catch (Exception ex) {
            updateConfigInfo(configAllInfo);
        }

        ConfigChangePublisher
                .notifyConfigChange(new LocalDataChangeEvent(ContentUtil.getGroupKey(configAllInfo)));
    }
    private Integer addConfigInfo(ConfigAllInfo config) {
        config.setContent(ContentUtil.getPoolContent(config));
        config.setMd5(Md5Util.getTpContentMd5(config));
        try {
            if (SqlHelper.retBool(configInfoMapper.insert(config))) {
                return config.getId();
            }
        } catch (Exception ex) {
            log.error("[db-error] message :: {}", ex.getMessage(), ex);
            throw ex;
        }
        return null;
    }

    private void updateConfigInfo(ConfigAllInfo config) {
        LambdaUpdateWrapper<ConfigAllInfo> wrapper = Wrappers.lambdaUpdate(ConfigAllInfo.class)
                .eq(ConfigAllInfo::getTpId, config.getTpId())
                .eq(ConfigAllInfo::getItemId, config.getItemId())
                .eq(ConfigAllInfo::getNamespace, config.getNamespace());

        config.setGmtCreate(null);
        config.setContent(ContentUtil.getPoolContent(config));
        config.setMd5(Md5Util.getTpContentMd5(config));
        try {
            configInfoMapper.update(config, wrapper);
        } catch (Exception ex) {
            log.error("[db-error] message :: {}", ex.getMessage(), ex);
            throw ex;
        }
    }
}
