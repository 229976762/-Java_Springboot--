package com.ghy.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RCode {

    fail(0,"操作失败!"),
    PASSWORD_ERROR(0,"密码错误"),
    LOGIN_ERROR(0,"用户名或密码错误！"),
    INVALID_TOKEN(1001,"登录身份过期"),
    ASSET_ADD_ERROR(0,"已存在该编号资产"),
    EMPLOYEES_INFO_ERROR(0,"保管人姓名不存在"),
    EMPLOYEES_ADD_ERROR(0,"已存在该员工信息"),
    CATE_ADD_ERROR(0,"分类代号冲突"),
    UPDATE_PASS_ERROR(0,"原密码错误"),

            ;

    private Integer code;
    private String msg;

}
