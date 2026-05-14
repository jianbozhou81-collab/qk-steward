package com.qk.dto;

import lombok.Data;

///businesses?businessId=21&name=李&phone=138012&status=1&assignName=张三&page=1&pageSize=10
/// 公海池分页查询请求参数和商机分页查询的请求参数虽有不同,但可以公用
@Data
public class BusinessDto {
    private Integer businessId;
    private String name;
    private String phone;
    private Integer subject;
    private Integer status;
    private String assignName;
    private Integer page = 1;
    private Integer pageSize = 10;
}
