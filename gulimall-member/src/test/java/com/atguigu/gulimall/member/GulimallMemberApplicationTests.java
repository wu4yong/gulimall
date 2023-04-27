package com.atguigu.gulimall.member;


import com.atguigu.gulimall.member.entity.UmsMemberEntity;
import com.atguigu.gulimall.member.service.UmsMemberService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Test
    public void testMd5Hex() {
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes()));
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes(), "$1$qqqqqqqq"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //$2a$10$GT0TjB5YK5Vx77Y.2N7hkuYZtYAjZjMlE6NWGE2Aar/7pk/Rmhf8S
        //$2a$10$cR3lis5HQQsQSSh8/c3L3ujIILXkVYmlw28vLA39xz4mHDN/NBVUi
        String encode = bCryptPasswordEncoder.encode("123456");
        boolean matches = bCryptPasswordEncoder.matches("123456", "$2a$10$GT0TjB5YK5Vx77Y.2N7hkuYZtYAjZjMlE6NWGE2Aar/7pk/Rmhf8S");

        System.out.println(encode + "==>" + matches);
    }

}
