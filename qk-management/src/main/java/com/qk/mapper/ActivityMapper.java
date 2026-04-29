package com.qk.mapper;

import com.qk.entity.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityMapper {
    /*
    活动列表分页查询
     */
    List<Activity> findActivities(Integer channel, Integer type, Integer status);
    /*
    根据id删除活动
     */
    @Delete("delete from activity where id = #{id}")
    void delete(Integer id);
    /*
    新增活动
     */
    @Insert("insert into activity (channel, name, start_time, end_time, description, type, discount, voucher, create_time, update_time) values (#{channel},#{name},#{startTime},#{endTime},#{description},#{type},#{discount},#{voucher},#{createTime},#{updateTime})")
    void addActivity(Activity activity);
    /*
    修改活动:根据id回显信息
     */
    @Select("select * from activity where id = #{id}")
    Activity findActivityById(Integer id);
    /*
    修改活动:修改回显的部门信息以达成修改
     */
    @Update("update activity set channel = #{channel}, name = #{name}, start_time = #{startTime}, end_time = #{endTime}, description = #{description}, type = #{type}, discount = #{discount}, voucher = #{voucher}, update_time = #{updateTime} where id = #{id}")
    void updateActivity(Activity activity);
    /*
    根据指定类型查询活动
     */
    @Select("select * from activity where type = #{type}")
    List<Activity> findActivityByType(Integer type);
}
