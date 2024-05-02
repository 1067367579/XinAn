package com.xinan.controller;

import com.github.pagehelper.Page;
import com.xinan.context.BaseContext;
import com.xinan.dto.MomentDTO;
import com.xinan.dto.MomentLikesDTO;
import com.xinan.result.PageResult;
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
        //再次获取一次用户id
        momentDTO.setUserId(BaseContext.getCurrentId());
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
        Long userId = BaseContext.getCurrentId();
        momentLikesDTO.setUserId(userId);
        log.info("用户{}对安心圈动态{}进行点赞",momentLikesDTO.getUserId(),momentLikesDTO.getMomentId());
        momentService.insertMomentLikes(momentLikesDTO);
        return Result.success();
    }

    @DeleteMapping("/likes/{momentId}")
    @ApiOperation(value = "用户根据安心圈id撤销点赞")
    public Result removeMomentLikes(@PathVariable Long momentId)
    {
        log.info("用户根据安心圈id撤销点赞:{}",momentId);
        momentService.removeMomentLikes(momentId);
        return Result.success();
    }

    @GetMapping
    @ApiOperation(value = "分页查看所有的安心圈")
    public Result<PageResult> listAll(@RequestParam Integer page,@RequestParam Integer pageSize)
    {
        log.info("查看所有的安心圈,页码:{},每页显示记录数:{}",page,pageSize);
        PageResult list = momentService.listAll(page,pageSize);
        return Result.success(list);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "分页查看指定用户的安心圈(查看别人的安心圈)")
    public Result<PageResult> getByUserId(@PathVariable Long userId,@RequestParam Integer page,@RequestParam Integer pageSize)
    {
        log.info("查看指定用户:{}的安心圈,页码:{},每页显示记录数:{}",userId,page,pageSize);
        PageResult list = momentService.getMomentsByUserId(userId,page,pageSize);
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除安心圈动态")
    public Result deleteById(@PathVariable Long id)
    {
        //默认前端 已经完成删除校验
        log.info("根据id删除安心圈动态:{}",id);
        momentService.deleteById(id);
        return Result.success();
    }
}
