package com.ghy.server.Exhandler;

import com.ghy.exceptionHandler.MyException;
import com.ghy.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result exceptionHandler(MyException e){
        log.error("出现异常！原因是:"+e.getMessage());
        return Result.error(e.getMessage(),e.getRCode().getCode());
    }
}
