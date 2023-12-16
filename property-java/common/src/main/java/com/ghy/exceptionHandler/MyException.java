package com.ghy.exceptionHandler;

import lombok.Getter;

@Getter
public class MyException extends Exception{
    private RCode rCode;

    public MyException(RCode rCode) {
        this.rCode = rCode;
    }

    @Override
    public String getMessage() {
        return rCode.getMsg();
    }
}
