package com.qk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 线索实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clue {
    /**
     * 线索ID，主键
     */
    private Integer id;

    /**
     * 手机号，唯一
     */
    private String phone;

    /**
     * 渠道来源，1:线上活动, 2:推广介绍
     */
    private Integer channel;

    /**
     * 活动ID，关联活动表主键
     */
    private Integer activityId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 性别，1:男, 2:女
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 归属人ID，关联用户表主键
     */
    private Integer userId;

    /**
     * 线索状态，1:待分配, 2:待跟进, 3:跟进中, 4:伪线索, 5:转为商机
     */
    private Integer status;

    /**
     * 意向学科，1:ai智能应用开发(java), 2:ai大模型开发(python)，3:ai鸿蒙开发，4:ai大数据，5:ai嵌入式，6:ai测试，7:ai运维
     */
    private Integer subject;

    /**
     * 意向等级, 1:近期学习、2:打算学习(考虑中)、3:进行了解、4:打酱油
     */
    private Integer level;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    //扩展属性
    /*
    分配人姓名
     */
    @TableField(exist = false)//不存在数据库中,只起到封装作用返回给前端看的
    private String assignName;
    /*
    追踪历史记录表
     */
    @TableField(exist = false)
    private List<ClueTrackRecord> trackRecords;
    /*
    跟进备注
     */
    @TableField(exist = false)
    private String record;
    /*
    活动名称
     */
    @TableField(exist = false)
    private String activityName;
}