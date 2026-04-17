package com.qk.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Course {
    private Integer id;
    private Integer subject;
    private String name;
    private Integer price;
    private Integer target;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
