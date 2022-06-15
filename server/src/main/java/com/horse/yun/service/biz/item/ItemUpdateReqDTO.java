package com.horse.yun.service.biz.item;

import lombok.Data;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:50
 */
@Data
public class ItemUpdateReqDTO {
    private String itemId;

    private String itemName;

    private String itemDesc;

    private String owner;
}
