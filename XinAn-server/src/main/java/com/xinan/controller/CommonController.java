package com.xinan.controller;

import com.xinan.constant.MessageConstant;
import com.xinan.exception.BaseException;
import com.xinan.result.Result;
import com.xinan.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user/common")
@Api(tags = "通用模块相关接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Result<String> upload(@RequestPart("image") MultipartFile image) {
        log.info("上传文件：{}",image);
        String originName = image.getOriginalFilename();
        //文件的扩展名
        String extName = originName.substring(originName.lastIndexOf("."));
        //随机生成新文件名
        String objectName = UUID.randomUUID()+extName;
        //上传，返回具体url地址
        String url = null;
        try {
            url = aliOssUtil.upload(image.getBytes(),objectName);
        } catch (IOException e) {
            log.info("文件上传失败:{}",e.getMessage());
            throw new BaseException(MessageConstant.UPLOAD_FAILED);
        }
        return Result.success(url);
    }

}
