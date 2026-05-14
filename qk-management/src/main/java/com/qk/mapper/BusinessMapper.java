package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.vo.ReportViewVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
    /*
    商机分页查询
     */
    Page<Business> listBusiness(Page<Object> objectPage, BusinessDto businessDto);
    /*
    根据id查询商机回显
     */
    Business findById(Integer id);
    /*
    统计各商机状态数
     */
    ReportViewVo countBusiness();
    /*
    商机公海分页查询
     */
    Page<Business> listBusinessBack(Page<Object> objectPage, BusinessDto businessDto);
}
