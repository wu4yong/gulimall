package com.atguigu.gulimall.thirdparty.service.impl;

import com.alibaba.fastjson.JSON;

import com.atguigu.gulimall.thirdparty.service.IQiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Date: 2021/6/11
 * @Author: wuyong
 * @Description: 七牛云服务实现类
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService {

    @Value("${qiniu.AccessKey}")
    private String accessKey;

    @Value("${qiniu.SecretKey}")
    private String secretKey;

    @Value("${qiniu.Bucket}")
    private String bucket;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String bucket = this.bucket;
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = file.getAbsolutePath();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = null;
        try {
            response = uploadManager.put(localFilePath, key, upToken);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        System.out.println(JSON.toJSON(response));
        return response;
    }


}
