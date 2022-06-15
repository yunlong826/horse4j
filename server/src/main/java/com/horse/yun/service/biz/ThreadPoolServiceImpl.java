package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.horse.yun.mapper.ConfigInfoMapper;
import com.horse.yun.model.ConfigAllInfo;
import com.horse.yun.service.biz.threadpool.ThreadPoolQueryReqDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolRespDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolSaveOrUpdateReqDTO;
import com.horse.yun.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:55
 */
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService{
    @Autowired
    private ConfigService configService;

    @Resource
    private ConfigInfoMapper configInfoMapper;

    @Override
    public IPage<ThreadPoolRespDTO> queryThreadPoolPage(ThreadPoolQueryReqDTO reqDTO) {
        LambdaQueryWrapper<ConfigAllInfo> wrapper = Wrappers.lambdaQuery(ConfigAllInfo.class)
                .eq(!StringUtils.isBlank(reqDTO.getTenantId()), ConfigAllInfo::getNamespace, reqDTO.getTenantId())
                .eq(!StringUtils.isBlank(reqDTO.getItemId()), ConfigAllInfo::getItemId, reqDTO.getItemId())
                .eq(!StringUtils.isBlank(reqDTO.getTpId()), ConfigAllInfo::getTpId, reqDTO.getTpId());
        return configInfoMapper.selectPage(reqDTO, wrapper).convert(each -> BeanUtil.convert(each, ThreadPoolRespDTO.class));
    }

    @Override
    public ThreadPoolRespDTO getThreadPool(ThreadPoolQueryReqDTO reqDTO) {
        ConfigAllInfo configAllInfo = configService.findConfigAllInfo(reqDTO.getTpId(), reqDTO.getItemId(), reqDTO.getTenantId());
        return BeanUtil.convert(configAllInfo, ThreadPoolRespDTO.class);
    }

    @Override
    public List<ThreadPoolRespDTO> getThreadPoolByItemId(String itemId) {
        List<ConfigAllInfo> selectList = configInfoMapper
                .selectList(Wrappers.lambdaUpdate(ConfigAllInfo.class).eq(ConfigAllInfo::getItemId, itemId));
        return BeanUtil.convert(selectList, ThreadPoolRespDTO.class);
    }

    @Override
    public void saveOrUpdateThreadPoolConfig(ThreadPoolSaveOrUpdateReqDTO reqDTO) {
        configService.insertOrUpdate(BeanUtil.convert(reqDTO, ConfigAllInfo.class));
    }
}
