package com.qk.service.impl;

import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueMapper;
import com.qk.service.ReportService;
import com.qk.vo.ReportViewVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    /*
    首页线索与商机数据展示
     */
    @Override
    public ReportViewVo overview() {
        //从Redis中获取数据
        Object overviewData =redisTemplate.opsForValue().get("OVERVIEW-DATA");
        if (overviewData != null){
            //数据存在,直接返回,第一次肯定没有存,所以第一次会向下继续执行
            log.info("从Redis中获取数据");
            return (ReportViewVo) overviewData;
        }
        //1.查询线索数据
        ReportViewVo cluereport =clueMapper.countClue();
        //2.查询商机数据
        ReportViewVo businessreport =businessMapper.countBusiness();
        //3.封装数据并返回
        BeanUtils.copyProperties(cluereport,businessreport,"businessTotal","businessWaitAllot","businessWaitFollow","businessFollowing","businessFalse","businessConvertCustomer");
        //4.保存数据到Redis中
        redisTemplate.opsForValue().set("OVERVIEW-DATA",businessreport,60, TimeUnit.SECONDS);//一分钟清空一次
        return businessreport;
    }
}
