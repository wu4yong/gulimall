package com.atguigu.gulimall.product.service;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.atguigu.gulimall.product.vo.SkuItemSaleAttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:20:38
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);

}

