package com.atguigu.gulimall.member.exception;

/**
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-27 12:00:00
 */
public class PhoneException extends RuntimeException {

    public PhoneException() {
        super("存在相同的手机号");
    }
}
