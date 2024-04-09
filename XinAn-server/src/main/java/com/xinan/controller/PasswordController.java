package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.MessageDTO;
import com.xinan.dto.PasswordDTO;
import com.xinan.entity.Password;
import com.xinan.result.Result;
import com.xinan.service.PasswordService;
import com.xinan.vo.PasswordSendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/password")
@Api(tags = "密码柜模块相关接口")
@Slf4j
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    //添加密码
    @ApiOperation(value = "添加密码")
    @PostMapping
    public Result addPassword(@RequestBody PasswordDTO passwordDTO)
    {
        log.info("添加密码:{}",passwordDTO);
        passwordService.addPassword(passwordDTO);
        return Result.success();
    }

    //修改密码
    @ApiOperation(value = "通过密码id修改密码")
    @PutMapping
    public Result updatePassword(@RequestBody Password password)
    {
        log.info("修改密码:{}",password);
        passwordService.updatePassword(password);
        return Result.success();
    }

    //发送密码
    @PostMapping("/send")
    @ApiOperation(value = "发送密码")
    public Result sendPassword(@RequestBody MessageDTO messageDTO)
    {
        log.info("发送密码:{}",messageDTO);
        passwordService.sendPassword(messageDTO);
        return Result.success();
    }

    //查看密码发送收件箱 点击接受时前端传过来包的id(在此处也就是密码id)
    @GetMapping("/receive")
    @ApiOperation(value = "查看密码发送收件箱")
    public Result<List<PasswordSendVO>> listMessage()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("查看密码发送收件箱:{}",userId);
        List<PasswordSendVO> vos = passwordService.listMessage(userId);
        return Result.success(vos);
    }

    //接受密码
    @PutMapping("/receive/{id}")
    @ApiOperation(value = "根据密码id接受密码")
    public Result receivePassword(@PathVariable Long id)
    {
        log.info("根据密码id接受密码:{}",id);
        //本质 直接修改密码表的userId
        passwordService.receivePassword(id);
        return Result.success();
    }

    //根据密码id删除密码
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据密码id删除密码")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据密码id删除密码:{}",id);
        passwordService.deleteById(id);
        return Result.success();
    }

    //查看全部密码信息
    @ApiOperation(value = "查看全部密码信息")
    @GetMapping
    public Result<List<Password>> listAll()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("查看当前用户全部密码信息:{}",userId);
        List<Password> list = passwordService.listAll(userId);
        return Result.success(list);
    }

    @ApiOperation(value = "根据密码id查看密码信息")
    @GetMapping("/{id}")
    public Result<Password> getById(@PathVariable Long id)
    {
        log.info("根据密码id查看密码信息:{}",id);
        Password password = passwordService.getById(id);
        return Result.success(password);
    }
}
