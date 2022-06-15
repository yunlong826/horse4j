package com.horse.yun.starter.remote;

import com.horse.yun.common.web.base.Result;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:10
 */
public interface HttpAgent {
    /**
     * 开始获取 NacosIp 集合
     */
    void start();

    /**
     * 获取命名空间
     *
     * @return
     */
    String getNameSpace();

    /**
     * 获取编码集
     *
     * @return
     */
    String getEncode();

    /**
     * 发起 Http Get 请求
     *
     * @param path
     * @param headers
     * @param paramValues
     * @param readTimeoutMs
     * @return
     */
    Result httpGet(String path, Map<String, String> headers, Map<String, String> paramValues,
                   long readTimeoutMs);

    /**
     * 发起 Http Post 请求
     *
     * @param path
     * @param headers
     * @param paramValues
     * @param readTimeoutMs
     * @return
     */
    Result httpPost(String path, Map<String, String> headers, Map<String, String> paramValues,
                    long readTimeoutMs);

    /**
     * 发起 Http Delete 请求
     *
     * @param path
     * @param headers
     * @param paramValues
     * @param readTimeoutMs
     * @return
     */
    Result httpDelete(String path, Map<String, String> headers, Map<String, String> paramValues,
                      long readTimeoutMs);
}
