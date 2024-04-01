package com.xinan.controller;

import com.xinan.dto.WishDTO;
import com.xinan.entity.Wish;
import com.xinan.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/wish")
@Slf4j
@Api(tags = "愿望清单模块相关接口")
public class WishController {

    //TODO 添加愿望接口 愿望内容为空时返回错误信息
    @PostMapping
    @ApiOperation(value = "添加愿望")
    public Result addWish(@RequestBody WishDTO wishDTO)
    {
        log.info("添加愿望:{}",wishDTO);
        return Result.success();
    }

    //TODO 修改愿望接口(状态/内容)
    @PutMapping
    @ApiOperation(value = "修改愿望(状态/内容)")
    public Result updateWish(@RequestBody Wish wish)
    {
        log.info("修改愿望:{}",wish);
        return Result.success();
    }

    //TODO 根据愿望id删除愿望
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据愿望id删除愿望")
    public Result deleteWish(@PathVariable Long id)
    {
        return Result.success();
    }

    //TODO 根据愿望id查询愿望内容 -- 用于修改回显
    @GetMapping("/{id}")
    @ApiOperation(value = "根据愿望id查询愿望内容")
    public Result getById(@PathVariable Long id)
    {
        return Result.success();
    }

    //TODO 查询当前用户所有愿望 -- 愿望清单页
    @GetMapping
    @ApiOperation(value = "查询当前用户的所有愿望")
    public Result<List<Wish>> listAll()
    {
        return Result.success();
    }
}
