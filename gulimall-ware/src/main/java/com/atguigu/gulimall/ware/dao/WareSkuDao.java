package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:32:57
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
