package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.ClueDto;
import com.qk.entity.Clue;
import com.qk.vo.ReportViewVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    /*
    线索分页查询
     */
    Page<Clue> listClue(Page<Clue> cluePage, ClueDto clueDto);
    /*
    根据id查询线索回显
     */
    Clue findById(Integer id);
    /*
    统计各线索状态数
     */
    ReportViewVo countClue();

}
