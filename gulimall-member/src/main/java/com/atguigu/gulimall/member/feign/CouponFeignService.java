package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 * 声明调用服务的服务名（nacos注册的名称）
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 编写feign接口
     * @return
     */
    @RequestMapping("/coupon/coupon/member/list")
    R membercoupons();

}
