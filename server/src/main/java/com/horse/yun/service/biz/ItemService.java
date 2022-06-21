package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horse.yun.service.biz.item.ItemQueryReqDTO;
import com.horse.yun.service.biz.item.ItemRespDTO;
import com.horse.yun.service.biz.item.ItemSaveReqDTO;
import com.horse.yun.service.biz.item.ItemUpdateReqDTO;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:48
 */
public interface ItemService {
    /**
     * 分页查询
     *
     * @param reqDTO
     * @return
     */
    IPage<ItemRespDTO> queryItemPage(ItemQueryReqDTO reqDTO);

    /**
     * 根据 Id 获取项目
     *
     * @param namespace
     * @param itemId
     * @return
     */
    ItemRespDTO queryItemById(String namespace, String itemId);

    /**
     * 查询项目
     *
     * @param reqDTO
     * @return
     */
    List<ItemRespDTO> queryItem(ItemQueryReqDTO reqDTO);

    /**
     * 新增项目
     *
     * @param reqDTO
     */
    void saveItem(ItemSaveReqDTO reqDTO);

    /**
     * 修改项目
     *
     * @param reqDTO
     */
    void updateItem(ItemUpdateReqDTO reqDTO);

    /**
     * 删除项目
     *
     * @param namespace
     * @param itemId
     */
    void deleteItem(String namespace, String itemId);
}
