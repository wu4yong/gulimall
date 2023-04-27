package com.atguigu.gulimall.member.exception;

/**
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-27 12:00:00
 */
public class UsernameException extends RuntimeException {


    public UsernameException() {
        super("存在相同的用户名");
    }
}
