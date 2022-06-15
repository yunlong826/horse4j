package com.horse.yun.starter.adapter;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.horse.yun.common.config.ApplicationContextHolder;
import com.horse.yun.starter.operation.ThreadPoolOperation;
import com.horse.yun.starter.wrap.DynamicThreadPoolWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 15:15
 */
public class ThreadPoolConfigAdapter extends ConfigAdapter{
    @Autowired
    private ThreadPoolOperation threadPoolOperation;

    private ExecutorService executorService = new ThreadPoolExecutor(
            2,
            4,
            0,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue(1),
            new ThreadFactoryBuilder().setNamePrefix("threadPool-config").build(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Order(1025)
    @PostConstruct
    public void subscribeConfig() {
        Map<String, DynamicThreadPoolWrap> executorMap =
                ApplicationContextHolder.getBeansOfType(DynamicThreadPoolWrap.class);

        List<String> tpIdList = new ArrayList();
        executorMap.forEach((key, val) -> tpIdList.add(val.getTpId()));

        tpIdList.forEach(each -> threadPoolOperation.subscribeConfig(each, executorService, config -> callbackConfig(config)));
    }
}
