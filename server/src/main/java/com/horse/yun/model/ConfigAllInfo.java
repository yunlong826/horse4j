package com.horse.yun.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.horse.yun.common.model.PoolParameter;
import lombok.Data;

import java.util.Date;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/15 15:40
 */
@Data
@TableName("config_info")
public class ConfigAllInfo extends ConfigInfo implements PoolParameter {
    /**
     * 简介
     */
    @JSONField(serialize = false)
    @TableField(exist = false, fill = FieldFill.UPDATE)
    private String desc;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 是否删除
     */
    @TableLogic
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
