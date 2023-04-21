package com.atguigu.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.atguigu.gulimall.thirdparty.service.IQiNiuService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Value("${qiniu.AccessKey}")
    private String accessKey;

    @Value("${qiniu.SecretKey}")
    private String secretKey;

    @Value("${qiniu.Bucket}")
    private String bucket;

    @Autowired
    OSSClient ossClient;

    @Autowired
    private IQiNiuService qiNiuService;


    @Test
    public void testUpload() {
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
        String localFilePath = "D:\\app\\qiniu\\oppo.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void testUpload1() throws FileNotFoundException {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "LTAI4FwvfjSycd1APnuG9bjj";
//        String accessKeySecret = "O6xaxyiWfSIitcOkSuK27ju4hXT5Hl";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\lfy\\Pictures\\Camera Roll\\59f2db04139a2.jpg");

        ossClient.putObject("gulimall-hello", "hahaha.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("上传完成...");
    }

    @Test
    public void testUploadFile() {
        // 文件目录
        String localFilePath = "D:\\app\\qiniu\\oppo.png";

        File file = new File(localFilePath);
        Assert.assertTrue(file.exists());
        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
