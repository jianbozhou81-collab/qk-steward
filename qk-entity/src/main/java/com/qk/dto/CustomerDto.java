package com.qk.dto;

import lombok.Data;
///customers?phone=13309091111&name=赵&channel=1&subject=1&page=1&pageSize=10
@Data
public class CustomerDto {
    private String phone;//手机号
    private String name;// 姓名
    private Integer channel;//渠道
    private Integer subject;//意向学科
    private Integer page=1;//页码
    private Integer pageSize=10;//每页条数

}
