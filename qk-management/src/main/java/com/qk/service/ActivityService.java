package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Activity;

import java.util.List;

public interface ActivityService {
    /*
    活动列表查询
     */
    PageResult<Activity> findActivities(Integer channel, Integer type, Integer status, Integer page, Integer pageSize);
    /*
    根据id删除活动
     */
    void delete(Integer id);
    /*
    新增活动
     */
    void addActivity(Activity activity);
    /*
    修改活动:根据id查询活动信息
     */
    Activity findActivityById(Integer id);
    /*
    修改活动:修改回显的部门信息以达成修改
     */
    void updateActivity(Activity activity);
    /*
    根据指定类型查询活动
     */
    List<Activity> findActivityByType(Integer type);

}
