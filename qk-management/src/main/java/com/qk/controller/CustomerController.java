package com.qk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.anno.OperateRecord;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.CustomerDto;
import com.qk.entity.Customer;
import com.qk.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    /*
    新增客户
     */
    @OperateRecord
    @PostMapping
    public Result addCustomer(@RequestBody Customer customer){
        //调用service层
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerService.save(customer);
        //响应数据
        return Result.success();
    }
    /*
    修改客户:数据回显
     */
    @GetMapping("/{id}")
    public Result findCustomerById(@PathVariable Integer id){
        //调用service层
        Customer customer = customerService.getById(id);
        //响应数据
        return Result.success(customer);
    }
    /*
    修改客户:修改回显的部门信息以达成修改
     */
    @PutMapping
    @OperateRecord
    public Result updateCustomer(@RequestBody Customer customer){
        //调用service层
        customer.setUpdateTime(LocalDateTime.now());
        customerService.updateById(customer);
        //响应数据
        return Result.success();
    }


}
