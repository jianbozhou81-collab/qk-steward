package com.qk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/businesses")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    /*
    商机分页查询
     */
    @GetMapping
    public Result findBusiness(BusinessDto businessDto) {
        log.info("查询商机:{}", businessDto);
        //调用service查询(自编)
        Page<Business> page =businessService.listBusiness(new Page<>(businessDto.getPage(), businessDto.getPageSize()),businessDto);
        return Result.success(new PageResult< Business>(page.getTotal(),page.getRecords()));
    }
    /*
    新增商机列表
     */
    @PostMapping
    public Result addBusiness(@RequestBody Business business) {
        log.info("新增商机:{}", business);
        //补全信息
        business.setStatus(1);//1 待分配
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        businessService.save(business);
        return Result.success();
    }
    /*
    商机分配(管理员才能进行的请求)
     */
    @PutMapping("/assign/{businessId}/{userId}")
    public Result assignBusiness(@PathVariable Integer businessId,@PathVariable Integer userId) {
        log.info("商机分配:{} {}", businessId, userId);
        //调用service分配
        Business business = new Business();//记住我创建的这个商机对象是没有任何属性的,
        //但是既然要根据id修改肯定要有原来的id附上去啊,所以下面的给id赋值不是多余的
        business.setId(businessId);//这里其实获得的还是商机表格的id,获取后又重新复制id,这不多余的吗
        business.setUserId(userId);
        business.setUpdateTime(LocalDateTime.now());
        business.setStatus(2);//待跟进
        businessService.updateById(business);
        //响应
        return Result.success();
    }
    /*
    跟进商机的数据回显
     */
    @GetMapping("/{id}")
    public Result findBusinessById(@PathVariable Integer id) {
        log.info("查询商机:{}", id);
        //调用service查询
        Business business = businessService.findById(id);
        return Result.success(business);
    }
    /*
    商机跟进
     */
    @PutMapping
    public Result updateBusiness(@RequestBody Business business) {
        log.info("商机跟进:{}", business);
        businessService.upsetBusiness(business);
        return Result.success();
    }
    /*
    踢回公海
     */
    @PutMapping("/back/{id}")
    public Result backBusiness(@PathVariable Integer id) {
        log.info("商机踢回公海:{}", id);
        //调用service查询
        Business business = new Business();
        business.setId(id);
        business.setStatus(4);//回收
        business.setUpdateTime(LocalDateTime.now());//就改这三个
        businessService.updateById(business);
        return Result.success();
    }
    /*
    公海池分页查询
     */
/*    @GetMapping("/pool")
    public Result findPool(BusinessDto businessDto) {
        log.info("查询公海:{}", businessDto);
        //调用service查询(自编)
        Page<Business> page =businessService.listBusinessBack(new Page<>(businessDto.getPage(), businessDto.getPageSize()),businessDto);
        return Result.success(new PageResult<Business>(page.getTotal(),page.getRecords()));
    }*/
    /*
    公海池分页查询
    利用wrapper进行查询
     */
    @GetMapping("/pool")
    public Result findPool2(BusinessDto businessDto) {
        log.info("查询公海:{}", businessDto);
        //调用service查询(逻辑交为复杂,再service层编写)
        Page<Business> page=businessService.findBusinessBack(businessDto);
        return Result.success(new PageResult< Business>(page.getTotal(),page.getRecords()));

    }
    /*
    转客户处理
     */
    @PutMapping("/toCustomer/{id}")
    public Result toCustomer(@PathVariable Integer id) {
        log.info("接收到要转客户的商机id:{}", id);
        //调用service查询
        businessService.toCustomer(id);
        return Result.success();
    }




}
