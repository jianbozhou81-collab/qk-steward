package com.qk.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Activity {
    /*
    注意localdatetime默认接收的json时间格式为yyyy-MM-ddTHH:mm:ss,发送请求的时候要转化一下格式
     */
    private Integer id;
    private Integer channel;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private Integer type;
    private Double discount;
    private Integer voucher;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
