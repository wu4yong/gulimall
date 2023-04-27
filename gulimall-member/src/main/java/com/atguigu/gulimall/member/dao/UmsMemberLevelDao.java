package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.UmsMemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
@Mapper
public interface UmsMemberLevelDao extends BaseMapper<UmsMemberLevelEntity> {
    UmsMemberLevelEntity getDefaultLevel();
}
