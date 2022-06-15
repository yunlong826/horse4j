package com.horse.yun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.web.base.Result;
import com.horse.yun.common.web.base.Results;
import com.horse.yun.service.biz.ThreadPoolService;
import com.horse.yun.service.biz.threadpool.ThreadPoolQueryReqDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolRespDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolSaveOrUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 17:00
 */
@RestController
@RequestMapping(Constants.BASE_PATH)
public class ThreadPoolController {
    @Autowired
    private ThreadPoolService threadPoolService;

    @PostMapping("/thread/pool/query/page")
    public Result<IPage<ThreadPoolRespDTO>> queryNameSpacePage(@RequestBody ThreadPoolQueryReqDTO reqDTO) {
        return Results.success(threadPoolService.queryThreadPoolPage(reqDTO));
    }

    @PostMapping("/thread/pool/query")
    public Result<ThreadPoolRespDTO> queryNameSpace(@RequestBody ThreadPoolQueryReqDTO reqDTO) {
        return Results.success(threadPoolService.getThreadPool(reqDTO));
    }

    @PostMapping("/thread/pool/save_or_update")
    public Result saveOrUpdateThreadPoolConfig(@RequestBody ThreadPoolSaveOrUpdateReqDTO reqDTO) {
        threadPoolService.saveOrUpdateThreadPoolConfig(reqDTO);
        return Results.success();
    }
}
