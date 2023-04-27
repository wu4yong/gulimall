package com.atguigu.gulimall.member;


import com.atguigu.gulimall.member.entity.UmsMemberEntity;
import com.atguigu.gulimall.member.service.UmsMemberService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@RunWith(SpringRunner.class)
//@SpringBootTest
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

    @Test
    public void testMd5Hex() {
        // e10adc3949ba59abbe56e057f20f883e
        // 抗修改性，   彩虹表
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);

        // MD5不能直接进行密码的加密存储
        //盐值加密： 随机值  加盐
        // $1$gcUUBbUS$3ecnXyPey8raKAKok.cZk1 前缀 $1$
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes()));
        // $1$qqqqqqqq$AZofg3QwurbxV3KEOzwuI1
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes(), "$1$qqqqqqqq"));

        // 密码加密器：
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // $2a$10$yutF.1XL6eT8rNb.F/0/3OYeMr5dYzXK13A5t40wcOk1TqrSzSe9q
        // $2a$10$48c/n2aWi66JwU7ZeHej.eOmk6UwcgoOAHXubrssPIvEJni.ZlVI6
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println("123456编码结果："+encode);
        boolean matches = bCryptPasswordEncoder.matches("123456", "$2a$10$yutF.1XL6eT8rNb.F/0/3OYeMr5dYzXK13A5t40wcOk1TqrSzSe9q");

        System.out.println(encode + "==>" + matches);
    }

}
