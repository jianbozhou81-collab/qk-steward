package com.qk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;

public interface CustomerService extends IService<Customer> {
    /*
    客户列表查询
     */
    Page<Customer> listCustomer(Page<Object> objectPage, CustomerDto customerDto);
}
