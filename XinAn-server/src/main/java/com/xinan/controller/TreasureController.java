package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.TreasureDTO;
import com.xinan.entity.Treasure;
import com.xinan.result.Result;
import com.xinan.service.TreasureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "珍宝收藏模块相关接口")
@RequestMapping("/user/treasure")
public class TreasureController {

    @Autowired
    private TreasureService treasureService;

    //添加珍宝
    @PostMapping
    @ApiOperation(value = "添加珍宝")
    public Result insert(@RequestBody TreasureDTO treasureDTO)
    {
        log.info("添加珍宝:{}",treasureDTO);
        treasureService.addTreasure(treasureDTO);
        return Result.success();
    }

    //删除珍宝
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据珍宝id删除珍宝收藏")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据珍宝id删除珍宝收藏:{}",id);
        treasureService.deleteById(id);
        return Result.success();
    }

    //修改珍宝
    @PutMapping
    @ApiOperation(value = "根据珍宝id修改珍宝收藏")
    public Result update(@RequestBody Treasure treasure)
    {
        log.info("根据珍宝id修改珍宝收藏:{}",treasure.getId());
        treasureService.update(treasure);
        return Result.success();
    }

    //查询用户所有珍宝收藏
    @GetMapping
    @ApiOperation(value = "查看用户所有珍宝")
    public Result<List<Treasure>> listAll()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("查看用户所有珍宝:{}",userId);
        List<Treasure> vo = treasureService.listAll(userId);
        return Result.success(vo);
    }

    //根据珍宝id查询珍宝收藏 -- 用于回显进行修改
    @GetMapping("/{id}")
    @ApiOperation(value = "根据珍宝id查询珍宝收藏")
    public Result<Treasure> getById(@PathVariable Long id)
    {
        log.info("根据珍宝ID查询珍宝收藏:{}",id);
        Treasure treasure = treasureService.getById(id);
        return Result.success(treasure);
    }
}
