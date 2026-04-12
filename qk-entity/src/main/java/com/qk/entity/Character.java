package com.qk.entity;

import lombok.Data;

import java.time.LocalDateTime;
/*角色管理请求接收的实体类*/
@Data
public class Character {
    private Integer id;//序号
    private String name;// 角色名称
    private String label;// 角色标识
    private String remark;// 备注
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 修改时间
}
