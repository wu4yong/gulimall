package com.atguigu.gulimall.member.service;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.member.entity.UmsMemberEntity;
import com.atguigu.gulimall.member.exception.PhoneException;
import com.atguigu.gulimall.member.exception.UsernameException;
import com.atguigu.gulimall.member.vo.MemberUserLoginVo;
import com.atguigu.gulimall.member.vo.MemberUserRegisterVo;
import com.atguigu.gulimall.member.vo.SocialUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 会员
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public interface UmsMemberService extends IService<UmsMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 用户注册
     * @param vo
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    UmsMemberEntity login(MemberUserLoginVo vo);

    /**
     * 社交用户的登录
     * @param socialUser
     * @return
     */
    UmsMemberEntity login(SocialUser socialUser) throws Exception;

    /**
     * 微信登录
     * @param accessTokenInfo
     * @return
     */
    UmsMemberEntity login(String accessTokenInfo);
}

