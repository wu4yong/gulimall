package com.atguigu.gulimall.member.vo;

import lombok.Data;

/**
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-27 12:00:00
 */
@Data
public class SocialUser {

    private String access_token;

    private String remind_in;

    private long expires_in;

    private String uid;

    private String isRealName;

}
