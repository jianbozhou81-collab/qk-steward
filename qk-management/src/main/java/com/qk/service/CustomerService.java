package com.qk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;

public interface CustomerService {
    /*
    客户列表查询
     */
    Page<Customer> listCustomer(Page<Object> objectPage, CustomerDto customerDto);
}
