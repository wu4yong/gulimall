package com.atguigu.gulimall.search.service;


import com.atguigu.common.es.SkuEsModel;

import java.io.IOException;
import java.util.List;


/**
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
