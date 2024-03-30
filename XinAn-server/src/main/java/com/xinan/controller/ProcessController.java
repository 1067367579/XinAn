package com.xinan.controller;

import com.xinan.dto.MerchantAddressDTO;
import com.xinan.dto.ProcessDTO;
import com.xinan.result.Result;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.ProcessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/process")
@Api(tags = "服务模块相关接口")
@Slf4j
public class ProcessController {

    //查看服务进度 按照用户查询
    @GetMapping("/{userId}")
    @ApiOperation(value = "按照用户id查询服务流程")
    public Result<ProcessVO> getByUserId(@PathVariable Long userId)
    {
        log.info("按照用户id查询服务流程:{}",userId);

        return Result.success();
    }

    //修改服务流程 添加 删除(批量删除)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "按照流程id批量删除流程")
    public Result deleteByIds(@PathVariable List<Long> ids)
    {
        return Result.success();
    }

    //添加流程 默认是该用户的最后一个流程
    @PutMapping
    @ApiOperation("添加流程 默认是该用户的最后一个流程")
    public Result addProcess(@RequestBody ProcessDTO processDTO)
    {
        return Result.success();
    }

    //条件查询商家
    @GetMapping("/merchants")
    @ApiOperation(value = "根据地址条件查询商家")
    public Result<List<MerchantVO>> listMerchants(@RequestBody MerchantAddressDTO merchantAddressDTO)
    {
        return Result.success();
    }

    //流程细则?

}
