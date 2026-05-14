package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    /*
    客户列表查询
     */
    Page<Customer> listCustomer(Page<Object> objectPage, CustomerDto customerDto);
}
