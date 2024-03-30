package com.xinan.controller;

import com.xinan.dto.MomentDTO;
import com.xinan.dto.MomentLikesDTO;
import com.xinan.result.Result;
import com.xinan.service.MomentService;
import com.xinan.vo.MomentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/moment")
@Api(tags = "安心圈模块相关接口")
@Slf4j
public class MomentController {

    @Autowired
    private MomentService momentService;

    @PostMapping
    @ApiOperation(value = "发布新的安心圈动态")
    public Result insertMoment(@RequestBody MomentDTO momentDTO)
    {
        log.info("发布安心圈动态:{}",momentDTO);
        momentService.insertMoment(momentDTO);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "改动安心圈动态")
    public Result updateMoment(@RequestBody MomentDTO momentDTO)
    {
        log.info("修改安心圈动态:{}",momentDTO);
        momentService.updateMoment(momentDTO);
        return Result.success();
    }

    @PostMapping("/likes")
    @ApiOperation(value = "用户对安心圈点赞操作")
    public Result addMomentLikes(@RequestBody MomentLikesDTO momentLikesDTO)
    {
        log.info("用户{}对安心圈动态{}进行点赞",momentLikesDTO.getUserId(),momentLikesDTO.getMomentId());
        momentService.insertMomentLikes(momentLikesDTO);
        return Result.success();
    }

    @DeleteMapping("/likes/{id}")
    @ApiOperation(value = "用户根据点赞id撤销点赞")
    public Result removeMomentLikes(@PathVariable Long id)
    {
        log.info("用户根据点赞id撤销点赞:{}",id);
        momentService.removeMomentLikes(id);
        return Result.success();
    }

    @GetMapping
    @ApiOperation(value = "查看所有的安心圈")
    public Result<List<MomentVO>> listAll()
    {
        log.info("查看所有的安心圈");
        List<MomentVO> list = momentService.listAll();
        return Result.success(list);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "查看指定用户的安心圈")
    public Result<List<MomentVO>> getByUserId(@PathVariable Long userId)
    {
        log.info("查看指定用户:{}的安心圈",userId);
        List<MomentVO> list = momentService.getMomentsByUserId(userId);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除安心圈动态")
    public Result deleteById(@PathVariable Long id)
    {
        log.info("根据id删除安心圈动态:{}",id);
        momentService.deleteById(id);
        return Result.success();
    }
}
