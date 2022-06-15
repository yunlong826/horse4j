package com.horse.yun.starter.listener;

import com.alibaba.fastjson.JSON;
import com.horse.yun.common.config.ApplicationContextHolder;
import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.model.PoolParameterInfo;
import com.horse.yun.common.web.base.Result;
import com.horse.yun.starter.common.CommonThreadPool;
import com.horse.yun.starter.config.DynamicThreadPoolProperties;
import com.horse.yun.starter.core.GlobalThreadPoolManage;
import com.horse.yun.starter.remote.HttpAgent;
import com.horse.yun.starter.remote.ServerHttpAgent;
import com.horse.yun.starter.util.BlockingQueueUtil;
import com.horse.yun.starter.wrap.DynamicThreadPoolWrap;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 线程池启动监听
 * @date 2022/6/15 14:52
 */
public class ThreadPoolRunListener {
    private final DynamicThreadPoolProperties dynamicThreadPoolProperties;
    public ThreadPoolRunListener(DynamicThreadPoolProperties properties) {
        this.dynamicThreadPoolProperties = properties;
    }
    @Order(1024)
    @PostConstruct
    public void run(){
        Map<String, DynamicThreadPoolWrap> executorMap =
                ApplicationContextHolder.getBeansOfType(DynamicThreadPoolWrap.class);
        for(Map.Entry<String,DynamicThreadPoolWrap> entry:executorMap.entrySet()){
            Map<String,String> queryStrMap = new HashMap<>(3);
            queryStrMap.put("tpId", entry.getValue().getTpId());
            queryStrMap.put("itemId", dynamicThreadPoolProperties.getItemId());
            queryStrMap.put("namespace", dynamicThreadPoolProperties.getNamespace());
            PoolParameterInfo ppi = null;
            HttpAgent httpAgent = new ServerHttpAgent(dynamicThreadPoolProperties);
            Result result = httpAgent.httpGet(Constants.CONFIG_CONTROLLER_PATH, null, queryStrMap, 3000L);
            if (result.isSuccess() && (ppi = JSON.toJavaObject((JSON) result.getData(), PoolParameterInfo.class)) != null) {
                // 使用相关参数创建线程池
                TimeUnit unit = TimeUnit.SECONDS;
                BlockingQueue workQueue = BlockingQueueUtil.createBlockingQueue(ppi.getQueueType(), ppi.getCapacity());
                ThreadPoolExecutor resultTpe = new ThreadPoolExecutor(ppi.getCoreSize(), ppi.getMaxSize(), ppi.getKeepAliveTime(), unit, workQueue);
                entry.getValue().setPool(resultTpe);
            } else if (entry.getValue().getPool() == null) {
                entry.getValue().setPool(CommonThreadPool.getInstance(entry.getValue().getTpId()));
            }

            GlobalThreadPoolManage.register(entry.getValue().getTpId(), ppi, entry.getValue());
        }
//        executorMap.forEach((key,val)->{
//            Map<String,String> queryStrMap = new HashMap<>(3);
//            queryStrMap.put("tpId", val.getTpId());
//            queryStrMap.put("itemId", dynamicThreadPoolProperties.getItemId());
//            queryStrMap.put("namespace", dynamicThreadPoolProperties.getNamespace());
//
//            PoolParameterInfo ppi = null;
//            HttpAgent httpAgent = new ServerHttpAgent(dynamicThreadPoolProperties);
//            Result result = httpAgent.httpGet(Constants.CONFIG_CONTROLLER_PATH, null, queryStrMap, 3000L);
//            if (result.isSuccess() && (ppi = JSON.toJavaObject((JSON) result.getData(), PoolParameterInfo.class)) != null) {
//                // 使用相关参数创建线程池
//                TimeUnit unit = TimeUnit.SECONDS;
//                BlockingQueue workQueue = BlockingQueueUtil.createBlockingQueue(ppi.getQueueType(), ppi.getCapacity());
//                ThreadPoolExecutor resultTpe = new ThreadPoolExecutor(ppi.getCoreSize(), ppi.getMaxSize(), ppi.getKeepAliveTime(), unit, workQueue);
//                val.setPool(resultTpe);
//            } else if (val.getPool() == null) {
//                val.setPool(CommonThreadPool.getInstance(val.getTpId()));
//            }
//
//            GlobalThreadPoolManage.register(val.getTpId(), ppi, val);
//        });
    }
}
