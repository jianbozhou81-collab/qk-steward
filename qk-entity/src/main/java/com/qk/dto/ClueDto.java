package com.qk.dto;

import lombok.Data;
/*
* 线索查询参数
* */
///clues?clueId=55&phone=13309091233&status=1&channel=1&assignName=张三&page=1&pageSize=5
@Data
public class ClueDto {
    private Integer clueId;
    private String phone;
    private Integer status;
    private Integer channel;
    private String assignName;
    private Integer page = 1;
    private Integer pageSize = 10;
}
