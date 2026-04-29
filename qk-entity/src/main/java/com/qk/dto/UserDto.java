package com.qk.dto;

import lombok.Data;
/*
这个是前端传送过来的参数对象,专门用来储存前端用传送参数过多的时候为了便于维护而设置的参数对象
 */
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
