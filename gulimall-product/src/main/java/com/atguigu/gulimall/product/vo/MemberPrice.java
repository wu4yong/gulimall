/**
  * Copyright 2019 bejson.com
  */
package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:20:38
 */
@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;

}
