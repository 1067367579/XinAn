package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.CheckListDTO;
import com.xinan.result.Result;
import com.xinan.service.CheckListService;
import com.xinan.vo.CheckListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/list")
@Api(tags = "清单页模块相关接口")
@Slf4j
public class ListController {

    @Autowired
    private CheckListService checkListService;

    //获取清单页信息 清单进度 各项状况
    @GetMapping
    @ApiOperation(value = "查询用户清单页状况")
    public Result<CheckListVO> getByUserId()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("根据用户id查询清单页信息:{}", userId);
        CheckListVO vo = checkListService.getByUserId(userId);
        return Result.success(vo);
    }

    //修改清单页某一项的状态
    @PutMapping
    @ApiOperation(value = "用户修改清单页状态")
    public Result updateList(@RequestBody CheckListDTO checkListDTO)
    {
        log.info("修改清单页状态:{}",checkListDTO);
        checkListService.updateList(checkListDTO);
        return Result.success();
    }

}
