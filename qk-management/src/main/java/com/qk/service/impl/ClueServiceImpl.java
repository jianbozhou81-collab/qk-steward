package com.qk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.ClueDto;
import com.qk.dto.FalseClueDto;
import com.qk.entity.Business;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueMapper;
import com.qk.mapper.CluetrackRecordMapper;
import com.qk.service.ClueService;
import com.qk.utils.CurrentUserHoler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {


    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private CluetrackRecordMapper cluetrackRecordMapper;
    @Autowired
    private BusinessMapper businessMapper;
    /*
    线索分页查询
     */
    @Override
    public PageResult<Clue> listClue(Page<Clue> cluePage, ClueDto clueDto) {
        //调用mapper查询
        Page<Clue> page = clueMapper.listClue(cluePage,clueDto);
        //响应数据
        return new PageResult<Clue>(page.getTotal(),page.getRecords());

    }
    /*
    线索查询回显
     */
    @Override
    public Clue findById(Integer id) {
        Clue clue = clueMapper.findById(id);
        return clue;
    }
    /*
    跟进线索
     */
    @Transactional(rollbackFor = Exception.class)//括号里面的内容是意味着遇到那些异常回滚,这里是全部异常都回滚
    //一般默认的是运行异常才回滚
    @Override
    public void upsetClue(Clue clue) {
        // 更新基本信息
        clue.setStatus(3);
        clue.setUpdateTime(LocalDateTime.now());
        clueMapper.updateById(clue);
        // 插入跟进记录
        ClueTrackRecord cluetrackrecord = new ClueTrackRecord();
        cluetrackrecord.setClueId(clue.getId());
        cluetrackrecord.setUserId(CurrentUserHoler.getCurrentUser());
        cluetrackrecord.setSubject(clue.getSubject());
        cluetrackrecord.setLevel(clue.getLevel());
        cluetrackrecord.setRecord(clue.getRecord());
        cluetrackrecord.setNextTime(clue.getNextTime());
        cluetrackrecord.setType(1);//正常跟进
        cluetrackrecord.setCreateTime(LocalDateTime.now());
        cluetrackRecordMapper.insert(cluetrackrecord);
    }
    /*
    转商机
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void toBusiness(Integer id) {
        //1.更新线索状态
        Clue clue = clueMapper.findById(id);
        clue.setStatus(5);//商机状态
        clue.setUpdateTime(LocalDateTime.now());
        clueMapper.updateById(clue);
        //2.添加商机
        Business business = new Business();
        BeanUtils.copyProperties(clue,business);//属性复制,将相同的属性名复制给商机
        //自定义不同的属性
        business.setId(null);//商机id自增
        business.setStatus(1);//待分配
        business.setUserId(null);//商机归属人id
        business.setClueId(id);//线索id
        business.setNextTime( null);
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());
        businessMapper.insert(business);
    }
    /*
    伪线索处理
     */
    @Override
    public void convertFalseClue(Integer id, FalseClueDto falseClueDto) {
        //更行线索状态
        Clue clue = new Clue();
        clue.setId(id);
        clue.setStatus(4);//伪线索状态
        clue.setUpdateTime(LocalDateTime.now());
        clueMapper.updateById(clue);
        //插入一次伪线索记录
        ClueTrackRecord cluetrackrecord = new ClueTrackRecord();
        cluetrackrecord.setClueId(id);//线索id
        cluetrackrecord.setUserId(CurrentUserHoler.getCurrentUser());//跟进人id
        cluetrackrecord.setRecord(falseClueDto.getRemark());//备注
        cluetrackrecord.setType(0);//伪线索状态
        cluetrackrecord.setFalseReason(falseClueDto.getReason());//伪线索原因
        cluetrackrecord.setCreateTime(LocalDateTime.now());//创建时间
        cluetrackRecordMapper.insert(cluetrackrecord);
    }
}
