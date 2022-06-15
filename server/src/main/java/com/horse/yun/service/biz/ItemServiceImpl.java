package com.horse.yun.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.horse.yun.enums.DelEnum;
import com.horse.yun.mapper.ItemInfoMapper;
import com.horse.yun.model.ItemInfo;
import com.horse.yun.service.biz.item.ItemQueryReqDTO;
import com.horse.yun.service.biz.item.ItemRespDTO;
import com.horse.yun.service.biz.item.ItemSaveReqDTO;
import com.horse.yun.service.biz.item.ItemUpdateReqDTO;
import com.horse.yun.service.biz.threadpool.ThreadPoolRespDTO;
import com.horse.yun.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:51
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Resource
    private ItemInfoMapper itemInfoMapper;

    @Autowired
    private ThreadPoolService threadPoolService;

    @Override
    public IPage<ItemRespDTO> queryItemPage(ItemQueryReqDTO reqDTO) {
        LambdaQueryWrapper<ItemInfo> wrapper = Wrappers.lambdaQuery(ItemInfo.class)
                .eq(!StringUtils.isEmpty(reqDTO.getItemId()), ItemInfo::getItemId, reqDTO.getItemId())
                .eq(!StringUtils.isEmpty(reqDTO.getItemName()), ItemInfo::getItemName, reqDTO.getItemName())
                .eq(!StringUtils.isEmpty(reqDTO.getTenantId()), ItemInfo::getTenantId, reqDTO.getTenantId())
                .eq(!StringUtils.isEmpty(reqDTO.getOwner()), ItemInfo::getOwner, reqDTO.getOwner());
        Page<ItemInfo> resultPage = itemInfoMapper.selectPage(reqDTO, wrapper);

        return resultPage.convert(each -> BeanUtil.convert(each, ItemRespDTO.class));
    }

    @Override
    public ItemRespDTO queryItemById(String namespace, String itemId) {
        LambdaQueryWrapper<ItemInfo> queryWrapper = Wrappers
                .lambdaQuery(ItemInfo.class)
                .eq(ItemInfo::getTenantId, namespace)
                .eq(ItemInfo::getItemId, itemId);
        ItemInfo itemInfo = itemInfoMapper.selectOne(queryWrapper);

        ItemRespDTO result = BeanUtil.convert(itemInfo, ItemRespDTO.class);
        return result;
    }

    @Override
    public List<ItemRespDTO> queryItem(ItemQueryReqDTO reqDTO) {
        LambdaQueryWrapper<ItemInfo> wrapper = Wrappers.lambdaQuery(ItemInfo.class)
                .eq(!StringUtils.isEmpty(reqDTO.getItemId()), ItemInfo::getItemId, reqDTO.getItemId())
                .eq(!StringUtils.isEmpty(reqDTO.getTenantId()), ItemInfo::getTenantId, reqDTO.getTenantId());

        List<ItemInfo> itemInfos = itemInfoMapper.selectList(wrapper);
        return BeanUtil.convert(itemInfos, ItemRespDTO.class);
    }

    @Override
    public void saveItem(ItemSaveReqDTO reqDTO) {
        ItemInfo itemInfo = BeanUtil.convert(reqDTO, ItemInfo.class);
        int insertResult = itemInfoMapper.insert(itemInfo);

        boolean retBool = SqlHelper.retBool(insertResult);
        if (!retBool) {
            throw new RuntimeException("插入失败.");
        }
    }

    @Override
    public void updateItem(ItemUpdateReqDTO reqDTO) {
        ItemInfo itemInfo = BeanUtil.convert(reqDTO, ItemInfo.class);
        int updateResult = itemInfoMapper.update(itemInfo, Wrappers
                .lambdaUpdate(ItemInfo.class).eq(ItemInfo::getItemId, reqDTO.getItemId()));
        boolean retBool = SqlHelper.retBool(updateResult);
        if (!retBool) {
            throw new RuntimeException("修改失败.");
        }
    }

    @Override
    public void deleteItem(String namespace, String itemId) {
        List<ThreadPoolRespDTO> itemList = threadPoolService.getThreadPoolByItemId(itemId);
        if (CollectionUtils.isNotEmpty(itemList)) {
            throw new RuntimeException("项目包含线程池引用, 删除失败.");
        }

        int updateResult = itemInfoMapper.update(new ItemInfo(),
                Wrappers.lambdaUpdate(ItemInfo.class)
                        .eq(ItemInfo::getTenantId, namespace)
                        .eq(ItemInfo::getItemId, itemId)
                        .set(ItemInfo::getDelFlag, DelEnum.DELETE.getIntCode()));
        boolean retBool = SqlHelper.retBool(updateResult);
        if (!retBool) {
            throw new RuntimeException("删除失败.");
        }
    }
}
