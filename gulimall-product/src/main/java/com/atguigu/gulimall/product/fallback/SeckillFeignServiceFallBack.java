package com.atguigu.gulimall.product.fallback;


import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * 属性&属性分组关联
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:20:37
 */

@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnume.TO_MANY_REQUEST.getCode(),BizCodeEnume.TO_MANY_REQUEST.getMsg());
    }
}
