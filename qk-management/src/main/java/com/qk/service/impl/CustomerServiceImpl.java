package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import com.qk.mapper.CustomerMapper;
import com.qk.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Page<Customer> listCustomer(Page<Object> objectPage, CustomerDto customerDto) {
        log.info("进行客户列表查询");
        Page<Customer> page = customerMapper.listCustomer(objectPage, customerDto);
        return page;
    }
}
