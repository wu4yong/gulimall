package com.atguigu.gulimall.thirdparty.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

/**
 * @Date: 2021/6/11
 * @Author: wuyong
 * @Description: 七牛云服务
 */

public interface IQiNiuService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file) throws QiniuException;


}
