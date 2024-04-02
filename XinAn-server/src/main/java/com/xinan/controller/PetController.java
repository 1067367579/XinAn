package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.MessageDTO;
import com.xinan.dto.PetDTO;
import com.xinan.entity.Pet;
import com.xinan.result.Result;
import com.xinan.vo.PetSendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/pet")
@Api(tags = "宠物模块相关接口")
@Slf4j
public class PetController {

    //TODO 添加宠物
    @PostMapping
    @ApiOperation(value = "添加宠物")
    public Result addPet(@RequestBody PetDTO petDTO)
    {
        log.info("添加宠物:{}",petDTO);
        return Result.success();
    }

    //TODO 赠送宠物 同时载入发送表
    @PostMapping("/send")
    @ApiOperation(value = "赠送宠物")
    public Result sendPet(@RequestBody MessageDTO messageDTO)
    {
        log.info("赠送宠物:{}",messageDTO);
        return Result.success();
    }

    //TODO 查看宠物赠送收件箱
    @GetMapping("/receive")
    @ApiOperation(value = "查看宠物赠送收件箱")
    public Result<List<PetSendVO>> listMessage()
    {
        log.info("查看当前用户宠物赠送收件箱");
        return Result.success();
    }

    //TODO 接受宠物
    @PutMapping("/receive/{id}")
    @ApiOperation(value = "根据信息id接受宠物")
    public Result receivePet(@PathVariable Long id)
    {
        log.info("根据信息id接受宠物:{}",id);
        return Result.success();
    }

    //查看宠物 -- 1. 查看全部宠物(用户id) 2.查看某个宠物信息(回显)
    //TODO 查看全部宠物(用户)
    @GetMapping
    @ApiOperation(value = "查看当前用户的全部宠物")
    public Result<List<Pet>> listAll()
    {
        log.info("查看当前用户的全部宠物:{}", BaseContext.getCurrentId());
        return Result.success();
    }

    //TODO 根据宠物id查询宠物
    @GetMapping("/{id}")
    @ApiOperation(value = "根据宠物id查询宠物")
    public Result<Pet> getById(@PathVariable Long id)
    {
        log.info("根据宠物id查询宠物:{}",id);
        return Result.success();
    }

    //TODO 根据宠物id删除宠物
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据宠物id删除宠物")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据宠物id删除宠物:{}",id);
        return Result.success();
    }

    //TODO 修改宠物信息
    @PutMapping
    @ApiOperation(value = "修改宠物信息")
    public Result updatePet(@RequestBody Pet pet)
    {
        log.info("修改宠物信息:{}",pet);
        return Result.success();
    }
}
