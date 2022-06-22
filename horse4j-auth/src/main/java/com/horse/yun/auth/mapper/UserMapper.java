package com.horse.yun.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.horse.yun.auth.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/6/22 12:57
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
