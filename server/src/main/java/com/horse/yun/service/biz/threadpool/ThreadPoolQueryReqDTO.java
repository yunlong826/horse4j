package com.horse.yun.service.biz.threadpool;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:53
 */
@Data
public class ThreadPoolQueryReqDTO extends Page {
    private String tenantId;

    private String itemId;

    private String tpId;

    private String tpName;
}
