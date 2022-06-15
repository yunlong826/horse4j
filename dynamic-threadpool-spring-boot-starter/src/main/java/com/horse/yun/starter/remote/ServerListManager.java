package com.horse.yun.starter.remote;

import com.horse.yun.starter.config.DynamicThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 14:39
 */
@Slf4j
public class ServerListManager {
    private static final String HTTPS = "https://";

    private static final String HTTP = "http://";

    private String serverAddrsStr;

    volatile List<String> serverUrls = new ArrayList();

    private volatile String currentServerAddr;

    private Iterator<String> iterator;

    private final DynamicThreadPoolProperties properties;
    public ServerListManager(DynamicThreadPoolProperties dynamicThreadPoolProperties) {
        this.properties = dynamicThreadPoolProperties;
        serverAddrsStr = properties.getServerAddr();

        if (!StringUtils.isEmpty(serverAddrsStr)) {
            List<String> serverAddrs = new ArrayList();
            String[] serverAddrsArr = this.serverAddrsStr.split(",");

            for (String serverAddr : serverAddrsArr) {
                if (serverAddr.startsWith(HTTPS) || serverAddr.startsWith(HTTP)) {
                    // TODO 固定写，后面优化
                    currentServerAddr = serverAddr;
                    serverAddrs.add(serverAddr);
                }
            }

            this.serverUrls = serverAddrs;
        }
    }
    public String getCurrentServerAddr() {
        if (StringUtils.isEmpty(currentServerAddr)) {
            iterator = iterator();
            currentServerAddr = iterator.next();
        }
        return currentServerAddr;
    }

    Iterator<String> iterator() {
        if (serverUrls.isEmpty()) {
            log.error("[iterator-serverlist] No server address defined!");
        }
        return new ServerAddressIterator(serverUrls);
    }

    private static class ServerAddressIterator implements Iterator<String> {

        final List<RandomizedServerAddress> sorted;

        final Iterator<RandomizedServerAddress> iter;

        public ServerAddressIterator(List<String> source) {
            sorted = new ArrayList<RandomizedServerAddress>();
            for (String address : source) {
                sorted.add(new RandomizedServerAddress(address));
            }
            Collections.sort(sorted);
            iter = sorted.iterator();
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public String next() {
            return null;
        }

        static class RandomizedServerAddress implements Comparable<RandomizedServerAddress> {

            static Random random = new Random();

            String serverIp;

            int priority = 0;

            int seed;

            public RandomizedServerAddress(String ip) {
                try {
                    this.serverIp = ip;
                    /*
                     change random scope from 32 to Integer.MAX_VALUE to fix load balance issue
                     */
                    this.seed = random.nextInt(Integer.MAX_VALUE);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public int compareTo(RandomizedServerAddress other) {
                if (this.priority != other.priority) {
                    return other.priority - this.priority;
                } else {
                    return other.seed - this.seed;
                }
            }
        }
    }
}
