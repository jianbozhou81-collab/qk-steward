package com.qk.vo;

import lombok.Data;

import java.io.Serializable;

/*
首页展示线索与商机各状态数
 */
@Data
public class ReportViewVo implements Serializable {//这个类继承Serializable接口,是为了实现序列化,方便传输数据

    private Integer clueTotal;//线索总数
    private Integer clueWaitAllot;//待分配线索数
    private Integer clueWaitFollow;//待跟进线索数
    private Integer clueFollowing;//跟进中线索数
    private Integer clueFalse;//伪线索数
    private Integer clueConvertBusiness;//转商机 线索数

    private Integer businessTotal;//商机总数
    private Integer businessWaitAllot;//待分配商机数
    private Integer businessWaitFollow;//待跟进商机数
    private Integer businessFollowing;//跟进中商机数
    private Integer businessFalse;//伪商机数
    private Integer businessConvertCustomer;//转客户商机数

}
