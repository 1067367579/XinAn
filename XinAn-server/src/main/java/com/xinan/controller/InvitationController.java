package com.xinan.controller;

import com.xinan.dto.InvitationDTO;
import com.xinan.result.Result;
import com.xinan.service.InvitationService;
import com.xinan.vo.InvitationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/invitation")
@Api(tags = "葬礼邀请模块相关接口")
@Slf4j
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    //葬礼邀请好友操作 修改好友表中的邀请状态
    @PutMapping
    @ApiOperation(value = "改变好友邀请状态")
    public Result inviteFriend(@RequestBody InvitationDTO invitationDTO)
    {
        log.info("改变好友邀请状态");
        invitationService.updateInvite(invitationDTO);
        return Result.success();
    }

    //根据用户id分类查询全部好友
    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id分类查询全部好友")
    public Result<List<InvitationVO>> listAll(@PathVariable Long id)
    {
        log.info("根据用户id分类查询全部好友:{}",id);
        List<InvitationVO> list = invitationService.listAllByCategoryId(id);
        return Result.success(list);
    }

}
