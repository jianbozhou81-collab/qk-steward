package com.qk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.ClueDto;
import com.qk.dto.FalseClueDto;
import com.qk.entity.Clue;

public interface ClueService extends IService<Clue> {
        /*
    线索查询列表
     */

    PageResult<Clue> listClue(Page<Clue> cluePage, ClueDto clueDto);
    /*
    线索查询回显
     */
    Clue findById(Integer id);
    /*
    跟进线索
     */
    void upsetClue(Clue clue);
    /*
    转商机
     */
    void toBusiness(Integer id);
    /*
    伪线索处理
     */
    void convertFalseClue(Integer id, FalseClueDto falseClueDto);
}
