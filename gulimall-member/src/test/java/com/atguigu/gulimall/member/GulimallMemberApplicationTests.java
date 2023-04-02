package com.atguigu.gulimall.member;


import com.atguigu.gulimall.member.entity.UmsMemberEntity;
import com.atguigu.gulimall.member.service.UmsMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallMemberApplicationTests {
    @Autowired
    UmsMemberService umsMemberService;

    @Test
    public void contextLoads() {
        UmsMemberEntity umsMemberEntity = new UmsMemberEntity();
        umsMemberEntity.setJob("电子厂");
        umsMemberService.save(umsMemberEntity);
        System.out.println("添加会员成功!");

    }

}
