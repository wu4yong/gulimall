package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:27:40
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
