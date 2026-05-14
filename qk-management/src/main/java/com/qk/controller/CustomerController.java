package com.qk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import com.qk.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    /*
    客户列表查询
     */
    @GetMapping
    public Result listCustomer(CustomerDto customerDto){
        log.info("接收客户列表查询请求参数 {}",customerDto);
        //调用service查询(多表查询自编)(第一个参数一定得是Page类型的才行)
        Page<Customer> page = customerService.listCustomer(new Page<>(customerDto.getPage(), customerDto.getPageSize()),customerDto);
        return Result.success(new PageResult<Customer>(page.getTotal(),page.getRecords()));
    }
}
