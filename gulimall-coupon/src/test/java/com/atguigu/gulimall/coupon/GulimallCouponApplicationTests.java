package com.atguigu.gulimall.coupon;


import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.atguigu.gulimall.coupon.service.CouponService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallCouponApplicationTests {


    @Autowired
    CouponService couponService;

    @Test
    public void contextLoads() {
        CouponEntity couponEntity= new CouponEntity();
        couponEntity.setCouponName("优惠卷名字");
        couponService.save(couponEntity);
        System.out.println("优化卷保存成功!");

    }

}
