package com.qk.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
public class AliyunOSSOperator {

/*    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    private static final String BUCKET_NAME = "java-web-oss123";
    private static final String REGION = "cn-beijing";*/
    // 配置参数少的情况可以一个一个用@value注解来获取
/*    @Value("${aliyun.oss.endpoint}")
    private String ENDPOINT;
    @Value("${aliyun.oss.bucketName}")
    private String BUCKET_NAME;
    @Value("${aliyun.oss.region}")
    private String REGION;*/
    //但是当配置参数很多的时候就得考虑把配置参数都封装到一个对象里面取这样方便维护和管理
    @Autowired
    private AliyunOSSOperators aliyunOSSOperators;

    public String upload(byte[] content, String objectName) throws Exception {
        // 获取阿里云OSS配置参数
        String ENDPOINT = aliyunOSSOperators.getEndpoint();
        String BUCKET_NAME = aliyunOSSOperators.getBucketName();
        String REGION = aliyunOSSOperators.getRegion();
        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = null;

        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint(ENDPOINT)
                    .credentialsProvider(new EnvironmentVariableCredentialsProvider())
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(REGION)
                    .build();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, new ByteArrayInputStream(content));
            // 上传文件
            ossClient.putObject(putObjectRequest);

            // 返回文件访问URL
            return "https://" + BUCKET_NAME + "." + ENDPOINT.substring(8) + "/" + objectName;
        } catch (Exception e) {
            log.error("Caught an OSSException: " + e.getMessage());
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}