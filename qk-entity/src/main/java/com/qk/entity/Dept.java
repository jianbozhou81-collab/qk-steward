package com.qk.entity;

import lombok.Data;
import java.time.LocalDateTime;

/*部门管理数据对应接收类*/
@Data
public class Dept {
    private Integer id;//序号
    private String name;// 部门名称
    private Integer status;// 状态
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 修改时间
}
