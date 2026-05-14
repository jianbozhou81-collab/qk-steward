package com.qk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.entity.BusinessTrackRecord;
import com.qk.entity.Customer;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.BusinessTrackRecordMapper;
import com.qk.mapper.CustomerMapper;
import com.qk.service.BusinessService;
import com.qk.utils.CurrentUserHoler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
@Slf4j
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {


    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessTrackRecordMapper businessTrackRecordMapper;
    @Autowired
    private CustomerMapper customerMapper;
    /*
        商机分页查询
         */
    @Override
    public Page<Business> listBusiness(Page<Object> objectPage, BusinessDto businessDto) {
        //调用mapper查询
        Page<Business> page = businessMapper.listBusiness(objectPage,businessDto);
        return page;
    }
    /*
    商机跟进 数据回显
     */
    @Override
    public Business findById(Integer id) {
        return businessMapper.findById(id);
    }
    /*
    商机跟进
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void upsetBusiness(Business business) {
        //更新信息
        business.setUpdateTime(LocalDateTime.now());
        business.setStatus(3);//跟进中
        businessMapper.updateById(business);
        //插入跟进记录
        BusinessTrackRecord businessTrackRecord = new BusinessTrackRecord();
        businessTrackRecord.setBusinessId(business.getId());//商机id
        businessTrackRecord.setUserId(CurrentUserHoler.getCurrentUser());//跟进人id
        businessTrackRecord.setNextTime(business.getNextTime());//下次跟进时间
        businessTrackRecord.setRecord(business.getRecord());//跟进记录
        businessTrackRecord.setTrackStatus(business.getTrackStatus());//跟进状态
        String keyItems = Arrays.toString(business.getKeyItems());//沟通重点
        businessTrackRecord.setKeyItems(keyItems);
        businessTrackRecord.setCreateTime(LocalDateTime.now());
        businessTrackRecordMapper.insert(businessTrackRecord);
    }
    /*
    公海池分页查询(xml映射)
     */
/*    @Override
    public Page<Business> listBusinessBack(Page<Object> objectPage, BusinessDto businessDto) {
        Page<Business> page = businessMapper.listBusinessBack(objectPage,businessDto);
        return page;
    }*/
    /*
    公海池分页查询(mybtisplus)
     */
    @Override
    public Page<Business> findBusinessBack(BusinessDto businessDto) {
        log.info("查询公海:{}", businessDto);
        //调用service查询(mybatisplus)
        //编写查询条件wrapper
        LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(businessDto.getBusinessId() != null,Business::getId,businessDto.getBusinessId())//商机id
                .like(businessDto.getName() != null && !businessDto.getName().isEmpty(),Business::getName,businessDto.getName())
                .like(businessDto.getPhone() != null && !businessDto.getPhone().isEmpty(),Business::getPhone,businessDto.getPhone())
                .eq(businessDto.getSubject() != null, Business::getSubject,businessDto.getSubject())
                .eq(Business::getStatus,4)
                .orderByDesc(Business::getUpdateTime);//顺序排序
        Page<Business> page = this.page(new Page<>(businessDto.getPage(), businessDto.getPageSize()), queryWrapper);
        return page;
    }
    /*
    转客户处理
     */
    @Override
    public void toCustomer(Integer id) {
        log.info("进行转客户处理");
        //更新商机状态
        Business business = new Business();
        business.setId(id);
        business.setStatus(5);//转客户状态
        business.setUpdateTime(LocalDateTime.now());
        businessMapper.updateById(business);
        //添加客户
        Business findBusiness = businessMapper.findById(id);
        Customer customer = new Customer();
        BeanUtils.copyProperties(findBusiness,customer);//相同属性拷贝
        //自定义不同属性
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customer.setBusinessId(findBusiness.getId());
        customerMapper.insert(customer);
        log.info("转客户成功");

    }
}
