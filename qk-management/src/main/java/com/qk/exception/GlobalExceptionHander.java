package com.qk.exception;

import com.qk.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("出现异常:{}",e);
        return Result.error("服务器异常");

    }
    /*
    捕获DuplicateKeyException异常
     */
    @ExceptionHandler
    public Result handleException(DuplicateKeyException e){
        //1.获取异常的详细信息
        String emsge = e.getMessage();
        //2.判断是否含有关键细节
        if (emsge.contains("Duplicate entry")){
            String subemsge = emsge.substring(emsge.indexOf("Duplicate entry"));
            String[] emsgArr = subemsge.split(" ");
            String realemsge = emsgArr[2];
            return Result.error(realemsge+"已存在");
            /*
            若是利用正则表达式捕获的话就是
            //1.匹配器
            Pattern p = Pattern.compile("Duplicate entry '(.+)'");
            Matcher m =p.matcher(emsge);
            while (m.find){
            m.group();
            }
            到这里我才发现,正则表达式的爬取以多个的,对于一个细致的匹配,用string的截取和分裂好一点
             */
        }
        return Result.error("服务器异常");
    }
    /*
    用户异常捕获
     */
    @ExceptionHandler
    public Result handleBuisnessException(BuisnessException e){
        log.error("出现异常:{}",e);
        return Result.error(e.getMessage());
    }
}
