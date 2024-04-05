package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.WishDTO;
import com.xinan.entity.Wish;
import com.xinan.result.Result;
import com.xinan.service.WishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/wish")
@Slf4j
@Api(tags = "愿望清单模块相关接口")
public class WishController {

    @Autowired
    private WishService wishService;

    //添加愿望接口 愿望内容为空时返回错误信息
    @PostMapping
    @ApiOperation(value = "添加愿望")
    public Result addWish(@RequestBody WishDTO wishDTO)
    {
        log.info("添加愿望:{}",wishDTO);
        wishService.addWish(wishDTO);
        return Result.success();
    }

    //修改愿望接口(状态/内容)
    @PutMapping
    @ApiOperation(value = "修改愿望(状态/内容)")
    public Result updateWish(@RequestBody Wish wish)
    {
        log.info("修改愿望:{}",wish);
        wishService.updateWish(wish);
        return Result.success();
    }

    //根据愿望id删除愿望
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据愿望id删除愿望")
    public Result deleteWish(@PathVariable Long id)
    {
        log.info("根据愿望id删除愿望:{}",id);
        wishService.deleteWish(id);
        return Result.success();
    }

    //根据愿望id查询愿望内容 -- 用于修改回显
    @GetMapping("/{id}")
    @ApiOperation(value = "根据愿望id查询愿望内容(修改回显)")
    public Result<Wish> getById(@PathVariable Long id)
    {
        log.info("根据愿望id查询愿望内容:{}",id);
        Wish wish = wishService.getById(id);
        return Result.success(wish);
    }

    //查询当前用户所有愿望 -- 愿望清单页
    @GetMapping
    @ApiOperation(value = "查询当前用户的所有愿望")
    public Result<List<Wish>> listAll()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("查询当前用户的所有愿望:{}",userId);
        List<Wish> list = wishService.listByUser(userId);
        return Result.success(list);
    }
}
