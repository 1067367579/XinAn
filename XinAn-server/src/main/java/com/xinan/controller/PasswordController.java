package com.xinan.controller;

import com.xinan.dto.MessageDTO;
import com.xinan.dto.PasswordDTO;
import com.xinan.entity.Password;
import com.xinan.result.Result;
import com.xinan.vo.PasswordSendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/password")
@Api(tags = "密码柜模块相关接口")
@Slf4j
public class PasswordController {

    //TODO 添加密码
    @ApiOperation(value = "添加密码")
    @PostMapping
    public Result addPassword(@RequestBody PasswordDTO passwordDTO)
    {
        log.info("添加密码:{}",passwordDTO);
        return Result.success();
    }

    //TODO 修改密码
    @ApiOperation(value = "修改密码")
    @PutMapping
    public Result updatePassword(@RequestBody Password password)
    {
        log.info("修改密码:{}",password);
        return Result.success();
    }

    //TODO 发送密码
    @PostMapping("/send")
    @ApiOperation(value = "发送密码")
    public Result sendPassword(@RequestBody MessageDTO messageDTO)
    {
        log.info("发送密码:{}",messageDTO);
        return Result.success();
    }

    //TODO 查看密码发送收件箱
    @GetMapping("/receive")
    @ApiOperation(value = "查看密码发送收件箱")
    public Result<List<PasswordSendVO>> listMessage()
    {
        log.info("查看密码发送收件箱");
        return Result.success();
    }

    //TODO 接受密码
    @PutMapping("/receive/{id}")
    @ApiOperation(value = "根据信息id接受密码")
    public Result receivePassword(@PathVariable Long id)
    {
        log.info("根据信息id接受密码:{}",id);
        return Result.success();
    }

    //根据密码id删除密码
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据密码id删除密码")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据密码id删除密码:{}",id);
        return Result.success();
    }

    //查看全部密码信息
    @ApiOperation(value = "查看全部密码信息")
    @GetMapping
    public Result<List<Password>> listAll()
    {
        log.info("查看全部密码信息");
        return Result.success();
    }

    @ApiOperation(value = "根据密码id查看密码信息")
    @GetMapping("/{id}")
    public Result<Password> getById(@PathVariable Long id)
    {
        log.info("根据密码id查看密码信息:{}",id);
        return Result.success();
    }
}
