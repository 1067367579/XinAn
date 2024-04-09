package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.PlatformDTO;
import com.xinan.entity.Platform;
import com.xinan.result.Result;
import com.xinan.service.PlatformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/platform")
@Api(tags = "社交平台管理模块相关接口")
@Slf4j
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    //添加社交平台
    @ApiOperation(value = "添加社交平台")
    @PostMapping
    public Result addPlatform(@RequestBody PlatformDTO platformDTO)
    {
        log.info("添加社交平台:{}",platformDTO);
        platformService.addPlatform(platformDTO);
        return Result.success();
    }

    //查询所有社交平台
    @ApiOperation(value = "查询所有社交平台")
    @GetMapping
    public Result<List<Platform>> listAll()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("查询该用户下所有社交平台信息:{}",userId);
        List<Platform> list = platformService.listAll(userId);
        return Result.success(list);
    }

    //修改社交平台管理信息
    @ApiOperation(value = "修改社交平台管理信息")
    @PutMapping
    public Result updatePlatform(@RequestBody Platform platform)
    {
        log.info("修改社交平台管理信息:{}",platform);
        platformService.update(platform);
        return Result.success();
    }

    //根据社交平台信息id删除社交平台
    @ApiOperation(value = "根据社交平台信息id删除社交平台")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据社交平台信息id删除社交平台:{}",id);
        platformService.deleteById(id);
        return Result.success();
    }

    //根据社交平台信息id查询社交平台信息
    @ApiOperation(value = "根据社交平台信息id查询社交平台信息")
    @GetMapping("/{id}")
    public Result<Platform> getById(@PathVariable Long id)
    {
        log.info("根据社交平台信息id查询社交平台信息:{}",id);
        Platform platform = platformService.getById(id);
        return Result.success(platform);
    }
}
