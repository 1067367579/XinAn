package com.xinan.controller;

import com.xinan.context.BaseContext;
import com.xinan.dto.MerchantAddressDTO;
import com.xinan.dto.ProcessDTO;
import com.xinan.entity.Merchant;
import com.xinan.entity.Process;
import com.xinan.entity.ProcessDetail;
import com.xinan.result.Result;
import com.xinan.service.ProcessService;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.ProcessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/process")
@Api(tags = "服务流程模块相关接口")
@Slf4j
public class ProcessController {

    @Autowired
    private ProcessService processService;

    //查看服务进度 按照用户查询
    @GetMapping
    @ApiOperation(value = "查询当前用户服务流程")
    public Result<List<ProcessVO>> getByUserId()
    {
        Long userId = BaseContext.getCurrentId();
        log.info("按照用户id查询服务流程:{}",userId);
        List<ProcessVO> list = processService.getByUserId(userId);
        return Result.success(list);
    }

    //删除服务流程 删除(批量删除)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "按照流程id批量删除流程")
    public Result<Object> deleteByIds(@PathVariable List<Long> ids)
    {
        log.info("根据流程id集合批量删除流程:{}",ids);
        //要把相关的流程细则id也删除
        processService.deleteBatch(ids);
        return Result.success();
    }

    //添加流程 默认是该用户的最后一个流程
    @PostMapping
    @ApiOperation("添加流程 默认是该用户的最后一个流程")
    public Result<Object> addProcess(@RequestBody ProcessDTO processDTO)
    {
        log.info("添加流程:{}",processDTO);
        processService.addProcess(processDTO);
        return Result.success();
    }

    //修改某一个流程的内容
    @PutMapping
    @ApiOperation(value = "根据流程id修改某一个流程的内容")
    public Result<Object> updateProcess(@RequestBody Process process)
    {
        log.info("根据流程ID修改某个流程的内容:{}",process);
        processService.updateProcess(process);
        return Result.success();
    }

    //条件查询商家
    //此处应该用请求参数的形式 而非json格式数据
    @GetMapping("/merchants")
    @ApiOperation(value = "根据地址条件查询商家")
    public Result<List<MerchantVO>> listMerchants(MerchantAddressDTO merchantAddressDTO)
    {
        log.info("根据地址条件查询商家:{}",merchantAddressDTO);
        List<MerchantVO> merchantVOS = processService.getMerchantByAddress(merchantAddressDTO);
        return Result.success(merchantVOS);
    }

    @PostMapping("/merchants")
    @ApiOperation(value = "添加商家")
    public Result<Object> addMerchants(Merchant merchant)
    {
        log.info("添加商家:{}",merchant);
        processService.addMerchant(merchant);
        return Result.success();
    }

    @GetMapping("/allMerchants")
    @ApiOperation(value = "获取全国商家")
    public Result<Object> getAllMerchants(@RequestParam int startIndex,@RequestParam int limit)
    {
        log.info("获取全国商家:{},{}",startIndex,limit);
        processService.addAllMerchant(startIndex,limit);
        return Result.success();
    }

    @PutMapping("/merchants")
    @ApiOperation(value = "修改商家")
    public Result<Object> updateMerchants(@RequestBody Merchant merchant)
    {
        log.info("修改商家:{}",merchant);
        processService.updateMerchant(merchant);
        return Result.success();
    }

    @DeleteMapping("/merchants/{id}")
    @ApiOperation(value = "删除商家")
    public Result<Object> deleteMerchants(@PathVariable Long id)
    {
        log.info("删除商家:{}",id);
        processService.deleteMerchant(id);
        return Result.success();
    }

    //改变流程顺序 逻辑改为删除掉现用户的所有流程之后 再按照现在的顺序进行添加
    @PutMapping("/order")
    @ApiOperation(value = "修改流程顺序")
    public Result<Object> updateProcessOrder(@RequestBody List<ProcessVO> processVOS)
    {
        log.info("根据数据修改流程顺序");
        //每个流程对应的流程细则怎么处理?
        //流程细则也全部删掉重新处理
        processService.updateProcessOrder(processVOS);
        return Result.success();
    }

    //增加流程细则
    @ApiOperation(value = "增加流程细则接口")
    @PostMapping("/detail")
    public Result<Object> addProcessDetail(@RequestBody ProcessDetail processDetail)
    {
        log.info("增加流程细则:{}",processDetail);
        processService.addProcessDetail(processDetail);
        return Result.success();
    }

    //修改流程细则
    @PutMapping("/detail")
    @ApiOperation(value = "根据流程细则id修改流程细则")
    public Result<Object> updateProcessDetail(@RequestBody ProcessDetail processDetail)
    {
        log.info("根据流程细则id修改流程细则:{}",processDetail.getId());
        processService.updateProcessDetail(processDetail);
        return Result.success();
    }

    //批量删除流程细则
    @ApiOperation(value = "根据id批量删除流程细则")
    @DeleteMapping("/detail/{ids}")
    public Result<Object> deleteProcessDetailBatch(@PathVariable List<Long> ids)
    {
        log.info("根据id批量删除流程细则:{}",ids);
        processService.deleteProcessDetailBatch(ids);
        return Result.success();
    }

    //改变流程细则顺序
    @PostMapping("/detail/order")
    @ApiOperation(value = "改变流程细则顺序")
    public Result<Object> updateProcessDetailOrder(@RequestBody List<ProcessDetail> processDetails)
    {
        //逻辑 先该流程下所有的流程细则 再重新放入数据库
        log.info("改变流程细则顺序");
        processService.updateProcessDetailOrder(processDetails);
        return Result.success();
    }
}
