package com.atguigu.gulimall.ware;


import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.atguigu.gulimall.ware.service.WareInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallWareApplicationTests {

    @Autowired
    WareInfoService wareInfoService;

    @Test
    public void contextLoads() {
        WareInfoEntity wareInfoEntity = new WareInfoEntity();
        wareInfoEntity.setName("四川乐山");
        wareInfoEntity.setAddress("井研县");
        wareInfoService.save(wareInfoEntity);
        System.out.println("仓库服务保存成功!");


    }

}
