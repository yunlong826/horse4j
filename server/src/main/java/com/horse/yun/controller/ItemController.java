package com.horse.yun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.common.constant.Constants;
import com.horse.yun.common.web.base.Result;
import com.horse.yun.common.web.base.Results;
import com.horse.yun.service.biz.ItemService;
import com.horse.yun.service.biz.item.ItemQueryReqDTO;
import com.horse.yun.service.biz.item.ItemRespDTO;
import com.horse.yun.service.biz.item.ItemSaveReqDTO;
import com.horse.yun.service.biz.item.ItemUpdateReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:48
 */
@RestController
@RequestMapping(Constants.BASE_PATH)
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/item/query/page")
    public Result<IPage<ItemRespDTO>> queryItemPage(@RequestBody ItemQueryReqDTO reqDTO) {
        return Results.success(itemService.queryItemPage(reqDTO));
    }

    @GetMapping("/item/query/{namespace}/{itemId}")
    public Result queryItemById(@PathVariable("namespace") String namespace, @PathVariable("itemId") String itemId) {
        return Results.success(itemService.queryItemById(namespace, itemId));
    }

    @PostMapping("/item/save")
    public Result saveItem(@RequestBody ItemSaveReqDTO reqDTO) {
        itemService.saveItem(reqDTO);
        return Results.success();
    }

    @PostMapping("/item/update")
    public Result updateItem(ItemUpdateReqDTO reqDTO) {
        itemService.updateItem(reqDTO);
        return Results.success();
    }

    @DeleteMapping("/item/delete/{namespace}/{itemId}")
    public Result deleteItem(@PathVariable("namespace") String namespace, @PathVariable("itemId") String itemId) {
        itemService.deleteItem(namespace, itemId);
        return Results.success();
    }
}
