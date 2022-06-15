package com.horse.yun.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.horse.yun.enums.DelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 16:03
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", Date.class, new Date());
        this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());

        this.strictInsertFill(metaObject, "delFlag", Integer.class, DelEnum.NORMAL.getIntCode());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());
    }
}
