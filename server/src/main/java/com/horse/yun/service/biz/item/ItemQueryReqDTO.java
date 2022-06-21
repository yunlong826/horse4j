package com.horse.yun.service.biz.item;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:49
 */
@Data
public class ItemQueryReqDTO extends Page {
    private String tenantId;

    private String itemId;

    private String itemName;

    private String owner;
}
