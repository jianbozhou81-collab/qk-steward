package com.qk.aspect;

import com.qk.entity.OperateLog;
import com.qk.mapper.OperateRecordMapper;
import com.qk.utils.CurrentUserHoler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateRecord {

    @Autowired
    private OperateRecordMapper operateRecordMapper;

    @Pointcut("@annotation(com.qk.anno.OperateRecord)")
    public void pt() {}


    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 补全操作记录
        OperateLog operatelog = new OperateLog();
        operatelog.setOperateUserId(CurrentUserHoler.getCurrentUser());
        operatelog.setOperateTime(LocalDateTime.now());
        operatelog.setClassName(pjp.getTarget().getClass().getName());
        operatelog.setMethodName(pjp.getSignature().getName());
        Object[] args = pjp.getArgs();
        operatelog.setMethodParams(Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = pjp.proceed();//执行调用的方法
        long end = System.currentTimeMillis();
        operatelog.setCostTime(end - start);


        operateRecordMapper.insert(operatelog);
        return result;
    }
}
