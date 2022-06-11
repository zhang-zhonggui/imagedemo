package com.zzg.imagedemo.util;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

public class OSSUtil {
    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    static String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static String accessKeyId = "LTAI5tRTcoGEvoL8A1ydLKEM";
    static String accessKeySecret = "XJVRfSRurHkZoWTeFJXwcLfTtyVzvM";
    // 填写Bucket名称，例如examplebucket。
    static String bucketName = "zhonggui";


    public static String uploadfile(String filePath, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, inputStream);
            return "https://" + bucketName + "." + endpoint +"/" + objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
