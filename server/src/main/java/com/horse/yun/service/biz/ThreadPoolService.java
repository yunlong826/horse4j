package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.service.biz.threadpool.ThreadPoolQueryReqDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolRespDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolSaveOrUpdateReqDTO;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:53
 */
public interface ThreadPoolService {
    /**
     * 分页查询线程池
     *
     * @param reqDTO
     * @return
     */
    IPage<ThreadPoolRespDTO> queryThreadPoolPage(ThreadPoolQueryReqDTO reqDTO);

    /**
     * 查询线程池配置
     *
     * @param reqDTO
     * @return
     */
    ThreadPoolRespDTO getThreadPool(ThreadPoolQueryReqDTO reqDTO);

    /**
     * 根据 ItemId 获取线程池配置
     *
     * @param itemId
     * @return
     */
    List<ThreadPoolRespDTO> getThreadPoolByItemId(String itemId);

    /**
     * 新增或修改线程池配置
     *
     * @param reqDTO
     */
    void saveOrUpdateThreadPoolConfig(ThreadPoolSaveOrUpdateReqDTO reqDTO);
}
