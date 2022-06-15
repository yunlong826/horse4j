package com.horse.yun.starter.remote;

import com.horse.yun.common.config.ApplicationContextHolder;
import com.horse.yun.common.web.base.Result;
import com.horse.yun.starter.config.DynamicThreadPoolProperties;
import com.horse.yun.starter.util.HttpClientUtil;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:39
 */
public class ServerHttpAgent implements HttpAgent{
    private final DynamicThreadPoolProperties dynamicThreadPoolProperties;

    private final ServerListManager serverListManager;

    private HttpClientUtil httpClientUtil = ApplicationContextHolder.getBean(HttpClientUtil.class);
    public ServerHttpAgent(DynamicThreadPoolProperties properties) {
        this.dynamicThreadPoolProperties = properties;
        this.serverListManager = new ServerListManager(dynamicThreadPoolProperties);
    }
    @Override
    public void start() {

    }

    @Override
    public Result httpGet(String path, Map<String, String> headers, Map<String, String> paramValues, long readTimeoutMs) {
        return httpClientUtil.restApiGetByThreadPool(buildUrl(path), headers, paramValues, readTimeoutMs, Result.class);
    }

    @Override
    public Result httpPost(String path, Map<String, String> headers, Map<String, String> paramValues, long readTimeoutMs) {
        return httpClientUtil.restApiPostByThreadPool(buildUrl(path), headers, paramValues, readTimeoutMs, Result.class);
    }

    @Override
    public Result httpDelete(String path, Map<String, String> headers, Map<String, String> paramValues, long readTimeoutMs) {
        return null;
    }

    @Override
    public String getNameSpace() {
        return dynamicThreadPoolProperties.getNamespace();
    }

    @Override
    public String getEncode() {
        return null;
    }

    private String buildUrl(String path) {
        return serverListManager.getCurrentServerAddr() + path;
    }
}
