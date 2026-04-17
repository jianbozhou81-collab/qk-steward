package com.qk.controller;

import com.qk.common.Result;
import com.qk.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /*  
    文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        // 1.接收图片上传保存到阿里云的oss仓库
        // 重新拼接文件名(要有规范)
        String originalFilename = image.getOriginalFilename(); //*******.png
        String substringname = originalFilename.substring(originalFilename.lastIndexOf(".")); // .png
        String filename = UUID.randomUUID().toString() + substringname;
        //调用工具类上传文件
        String url = aliyunOSSOperator.upload(image.getBytes(), filename);
        // 2.响应数据
        return Result.success(url);
    }
}
