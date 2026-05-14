package com.qk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import org.springframework.stereotype.Service;

@Service
public interface BusinessService extends IService<Business> {
    /*
    商机列表查询
     */
    Page<Business> listBusiness(Page<Object> objectPage, BusinessDto businessDto);
    /*
    商机跟进 数据回显
     */
    Business findById(Integer id);
    /*
    商机跟进
     */
    void upsetBusiness(Business business);
    /*
    公海池分页查询(xml文件映射)
     */
/*    Page<Business> listBusinessBack(Page<Object> objectPage, BusinessDto businessDto);*/
    /*
    公海池分页查询(mybatisplus)
     */
    Page<Business> findBusinessBack(BusinessDto businessDto);
    /*
    转客户处理
     */
    void toCustomer(Integer id);
}
