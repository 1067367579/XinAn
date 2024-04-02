package com.xinan.controller;

import com.xinan.dto.PlatformDTO;
import com.xinan.entity.Platform;
import com.xinan.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/platform")
@Api(tags = "社交平台管理模块相关接口")
@Slf4j
public class PlatformController {

    //TODO 添加社交平台
    @ApiOperation(value = "添加社交平台")
    @PostMapping
    public Result addPlatform(@RequestBody PlatformDTO platformDTO)
    {
        log.info("添加社交平台:{}",platformDTO);
        return Result.success();
    }

    //TODO 查询所有社交平台
    @ApiOperation(value = "查询所有社交平台")
    @GetMapping
    public Result<List<Platform>> listAll()
    {
        log.info("查询该用户下所有社交平台信息");
        return Result.success();
    }

    //TODO 修改社交平台管理信息
    @ApiOperation(value = "修改社交平台管理信息")
    @PutMapping
    public Result updatePlatform(@RequestBody Platform platform)
    {
        log.info("修改社交平台管理信息:{}",platform);
        return Result.success();
    }

    //TODO 根据社交平台信息id删除社交平台
    @ApiOperation(value = "根据社交平台信息id删除社交平台")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据社交平台信息id删除社交平台:{}",id);
        return Result.success();
    }

    //TODO 根据社交平台信息id查询社交平台信息
    @ApiOperation(value = "根据社交平台信息id查询社交平台信息")
    @GetMapping("/{id}")
    public Result<Platform> getById(@PathVariable Long id)
    {
        log.info("根据社交平台信息id查询社交平台信息:{}",id);
        return Result.success();
    }
}
