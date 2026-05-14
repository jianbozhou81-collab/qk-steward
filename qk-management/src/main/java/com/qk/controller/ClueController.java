package com.qk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.ClueDto;
import com.qk.dto.FalseClueDto;
import com.qk.entity.Clue;
import com.qk.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/clues")
@RestController // 表示该类为控制器
public class ClueController {
    @Autowired
    private ClueService clueService;

    /*
    添加线索
     */
    @PostMapping
    public Result addClue(@RequestBody Clue  clue) {
        log.info("添加线索:{}", clue);
        //补全信息
        clue.setStatus(1);//1 待分配
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clueService.save(clue);
        return Result.success();
    }
    /*
    查找线索
     */
    @GetMapping
    public Result findClue(ClueDto clueDto) {
        log.info("查询线索:{}", clueDto);
        //调用service查询(自编)
        PageResult<Clue> pageResult =clueService.listClue(new Page<Clue>(clueDto.getPage(),clueDto.getPageSize()),clueDto);
        //响应 数据
        return Result.success(pageResult);
    }
    /*
    线索分配
     */
    @PutMapping("/assign/{clueId}/{userId}")
    public Result assignClue(@PathVariable Integer clueId,@PathVariable Integer userId) {
        log.info("线索分配:{} {}", clueId, userId);
        //调用service分配
        Clue cule = new Clue();
        cule.setId(clueId);
        cule.setUserId(userId);
        cule.setStatus(2); //跟进状态
        cule.setUpdateTime(LocalDateTime.now());

        clueService.updateById(cule);//这里只改set设置过的,其他没设置的字段不修改
        //响应
        return Result.success();
    }
    /*
    根据id查询线索回显
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询线索:{}", id);
        //调用service查询
        Clue clue = clueService.findById(id);
        //响应数据
        return Result.success(clue);
    }
    /*
    线索跟进
     */
    @PutMapping
    public Result updateClue(@RequestBody Clue clue) {
        log.info("线索跟进:{}", clue);
        clueService.upsetClue(clue);
        return Result.success();
    }
    /*
    转商机
     */
    @PutMapping("/toBusiness/{id}")
    public Result toBusiness(@PathVariable Integer id) {
        log.info("转商机:{}", id);
        //调用service转商机
        clueService.toBusiness(id);
        //响应
        return Result.success();
    }
    /*
    伪线索处理
     */
    @PutMapping("/false/{id}")
    public Result falseClue(@PathVariable Integer id, @RequestBody FalseClueDto falseClueDto) {
        log.info("伪线索处理:{}", id);
        log.info("处理参数:{}", falseClueDto);
        //调用service处理
        clueService.convertFalseClue(id,falseClueDto);
        //响应
        return Result.success();
    }
    /*
    线索池查询
     */
    @GetMapping("/pool")
    public Result findCluePool(ClueDto clueDto) {
        //调用service查询(设计多表查询,自编)
        PageResult<Clue> pageResult=clueService.findCluePool(new Page<Clue>(clueDto.getPage(),clueDto.getPageSize()),clueDto);
        //响应数据
        return Result.success(pageResult);
    }


}
