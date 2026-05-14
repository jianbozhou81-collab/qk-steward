package com.qk.controller;

import com.qk.common.Result;
import com.qk.service.ReportService;
import com.qk.vo.ReportViewVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    /*
    首页线索和商机统计报表
     */
    @GetMapping("/overview")
    public Result overview() {
        log.info("首页线索和商机统计报表");
        //调用service查询
        ReportViewVo reportViewVo = reportService.overview();
        return Result.success(reportViewVo);
    }
}
