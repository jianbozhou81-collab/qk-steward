package com.qk.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private Integer status;
    private String phone;
    private Integer deptId;
    private Integer roleId;
    private Integer page=1;
    private Integer pageSize=10;
}
