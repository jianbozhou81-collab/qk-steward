package com.qk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qk.common.PageResult;
import com.qk.entity.Activity;
import com.qk.mapper.ActivityMapper;
import com.qk.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class ActivityServicerImpl implements ActivityService {


    @Autowired
    private ActivityMapper activityMapper;
    /*
    查询活动列表
     */
    @Override
    public PageResult<Activity> findActivities(Integer channel, Integer type, Integer status, Integer page, Integer pageSize) {
        //1.调用第三方插件进行分页
        PageHelper.startPage(page,pageSize);
        //2.调用mapper层进行普通查询
        List<Activity> list = activityMapper.findActivities(channel,type,status);
        //3.解析数据
        Page<Activity> pageList = (Page<Activity>) list;
        //4.响应数据
        return new PageResult<>(pageList.getTotal(),pageList.getResult());

    }
    /*
    根据id删除活动
     */
    @Override
    public void delete(Integer id) {
        // 1.调用mapper层进行删除
        activityMapper.delete(id);
    }
    /*
    添加活动
     */
    @Override
    public void addActivity(Activity activity) {
        //1.补全时间
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层进行添加
        activityMapper.addActivity(activity);
    }
    /*
    修改活动:根据id回显活动信息
     */
    @Override
    public Activity findActivityById(Integer id) {
        //1.调用mapper层进行查询
        Activity activity = activityMapper.findActivityById(id);
        //2.响应数据
        return activity;
    }
    /*
    修改活动:修改回显的部门信息以达成修改
     */
    @Override
    public void updateActivity(Activity activity) {
        //1.补全修改时间
        activity.setUpdateTime(LocalDateTime.now());
        //2.调用mapper层进行修改
        activityMapper.updateActivity(activity);
    }
    /*
    根据指定类型查询活动
     */
    @Override
    public List<Activity> findActivityByType(Integer type) {
        //1.调用mapper层进行查询
        List<Activity> list = activityMapper.findActivityByType(type);
        //2.响应数据
        return list;

    }
}
