package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Activity;
import com.qk.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    /*
    活动列表查询
     */
    @GetMapping
    public Result findActivities(Integer channel, Integer type, Integer status,@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer pageSize){
        log.info("查询活动列表:{}",channel,type,status,page,pageSize);
        //1.调用service层
        PageResult<Activity> list = activityService.findActivities(channel,type,status,page,pageSize);
        //2.响应数据
        return Result.success(list);
    }
    /*
    活动删除:根据id删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除活动:{}",id);
        // 1.调用service层
        activityService.delete(id);
        // 2.响应数据
        return Result.success();
    }
    /*
    新赠活动
     */
    @PostMapping
    public Result addActivity(@RequestBody Activity activity){
        /*        修正开始时间和结束时间的的时间格式(
          传送过来的时间格式为:
          "startTime": "2025-06-01 00:00:00",
          "endTime": "2025-06-30 23:59:59",)
        但是localDateTime.parse()方法不能识别这种格式:
        默认的 LocalDateTimeDeserializer 期望的格式是 ISO-8601 格式（如 2025-06-01T00:00:00）*/
        log.info("新增活动:{}",activity);
        //1.调用service层
        activityService.addActivity(activity);
        //2.响应数据
        return Result.success();
    }
    /*
    修改活动:根据id查询活动信息
     */
    @GetMapping("/{id}")
    public Result findActivityById(@PathVariable Integer id){
        log.info("查询活动:{}",id);
        //1.调用service层
        Activity activity = activityService.findActivityById(id);
        //2.响应数据
        return Result.success(activity);
    }
    /*
    修改活动:修改回显的部门信息以达成修改
     */
    @PutMapping
    public Result updateActivity(@RequestBody Activity activity){
        log.info("修改活动:{}",activity);
        //1.调用service层
        activityService.updateActivity(activity);
        //2.响应数据
        return Result.success();
    }
    /*
    根据指定类型查询活动
     */
    @GetMapping("/type/{type}")
    public Result findActivityByType(@PathVariable Integer type){
        log.info("查询活动:{}",type);
        //1.调用service层
        List<Activity> list = activityService.findActivityByType(type);
        //2.响应数据
        return Result.success(list);
    }


}
