package com.xinan.handler;

import com.xinan.constant.MessageConstant;
import com.xinan.exception.AccountNotFoundException;
import com.xinan.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理账号不存在异常的处理器
     * @param ex 账号无法找到异常
     * @return result格式数据返回到前端
     */
    @ExceptionHandler
    public Result exceptionHandler(AccountNotFoundException ex)
    {
        log.error("异常信息: {}",ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理sql异常的处理器
     * @param ex sql异常
     * @return result格式数据返回到前端
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex)
    {
        String message = ex.getMessage();
        log.error("异常信息: {}",message);
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }


}
