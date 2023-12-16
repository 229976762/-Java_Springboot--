package com.ghy.server.aop;

import com.ghy.exceptionHandler.MyException;
import com.ghy.exceptionHandler.RCode;
import com.ghy.form.LoginForm;
import com.ghy.result.Result;
import com.ghy.server.generator.domain.Employee;
import com.ghy.server.generator.service.EmployeeService;
import com.ghy.util.MD5Util;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author aaa
 */
@Aspect
@Component
@Slf4j
public class MyAspect {

    @Resource
    private EmployeeService employeeService;


    /**
     * 登录代理
     */
    @Pointcut("execution(* com.ghy.server.generator.control.CommonController.login(..))")
    public void loginAspect(){}


    /**
     * 登录校验
     */
    @Around(value = "loginAspect()")
    public Result loginAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        LoginForm loginForm = (LoginForm) args[0];

        Employee employee = employeeService.getById(loginForm.getUsername());
        if (employee == null || (!employee.getPassword().equals(MD5Util.md5(loginForm.getPassword())))
                                || (employee.getIsManager() != Integer.valueOf(loginForm.getUser()))) {
            throw new MyException(RCode.LOGIN_ERROR);
        }

        return (Result) joinPoint.proceed();
    }

//    /**
//     * 修改密码
//     */
//    @Around(value = "execution(* com.ghy.server.generator.control.CommonController.changePassword(..))")
//    public Result changePassword(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        Object[] args = joinPoint.getArgs();
//        ChangePwFrom from = (ChangePwFrom) args[0];
//
//        String oldPw = MD5Util.md5(from.getOldPass());
//        if("student".equals(from.getUser())) {
//            Student user = studentService.getById(from.getId());
//            if(user.getPw().equals(oldPw)){
//                user.setPw(MD5Util.md5(from.getCheckPass()));
//                studentService.updateById(user);
//            }else
//                throw new MyException(RCode.PASSWORD_ERROR);
//        } else if ("teacher".equals(from.getUser())) {
//            Teacher user = teacherService.getById(from.getId());
//            if(user.getPw().equals(oldPw)){
//                user.setPw(MD5Util.md5(from.getCheckPass()));
//                teacherService.updateById(user);
//            }else
//                throw new MyException(RCode.PASSWORD_ERROR);
//        }else{
//            Admin user = adminService.getById(from.getId());
//            if(user.getPw().equals(oldPw)){
//                user.setPw(MD5Util.md5(from.getCheckPass()));
//                adminService.updateById(user);
//            }else
//                throw new MyException(RCode.PASSWORD_ERROR);
//        }
//
//        Result result = (Result) joinPoint.proceed();
//        return result.success();
//    }
//
//
//    /**
//     * 查看个人信息
//     */
//    @Around(value = "execution(* com.ghy.server.generator.control.CommonController.selfInfo(..))")
//    public Result selfInfo(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//
//        SelfInfoFrom response = UserToFrom((String) args[1], (Integer) args[0]);
//
//        Result result = (Result) joinPoint.proceed();
//        return result.success(response);
//
//    }
//
//    /**
//     * 修改个人信息
//     */
//    @Around(value = "execution(* com.ghy.server.generator.control.CommonController.updateInfo(..))")
//    public Result updateInfo(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//
//        SelfInfoFrom from = (SelfInfoFrom) args[0];//更新信息
//        if("student".equals(from.getUser())) {
//            Student user = (Student) FromToUser(from);
//            studentService.updateById(user);
//        } else if ("teacher".equals(from.getUser())) {
//            Teacher user = (Teacher) FromToUser(from);
//            teacherService.updateById(user);
//        }else{
//            Admin user = (Admin) FromToUser(from);
//            adminService.updateById(user);
//        }
//
//        Result result = (Result) joinPoint.proceed();
//        return result.success(from);
//    }
//
//
//    public SelfInfoFrom UserToFrom(String user, Integer id){
//        SelfInfoFrom response = new SelfInfoFrom();
//        response.setId(id);
//
//        if("student".equals(user)) {
//            Student info = studentService.getById(id);
//            response.setName(info.getSname());
//            response.setBirth(info.getSbirth());
//            response.setPt(info.getSpt());
//            response.setMa(info.getSma());
//            response.setSex(info.getSsex());
//        } else if ("teacher".equals(user)) {
//            Teacher info = teacherService.getById(id);
//            response.setName(info.getTname());
//            response.setBirth(info.getTbirth());
//            response.setPt(info.getTpt());
//            response.setMa(info.getTma());
//            response.setSex(info.getTsex());
//        }else{
//            Admin info = adminService.getById(id);
//            response.setName(info.getAname());
//            response.setBirth(info.getAbirth());
//            response.setPt(info.getApt());
//            response.setMa(info.getAma());
//            response.setSex(info.getAsex());
//        }
//
//        return response;
//    }
//    public Object FromToUser(SelfInfoFrom from){
//
//        Object user = null;
//        if("student".equals(from.getUser())) {
//            user = new Student(from.getId(),from.getName()
//                    ,from.getSex(),from.getBirth(),from.getPt(),from.getMa());
//
//        } else if ("teacher".equals(from.getUser())) {
//            user = new Teacher(from.getId(),from.getName()
//                    ,from.getSex(),from.getBirth(),from.getPt(),from.getMa());
//        }else{
//            user = new Admin(from.getId(),from.getName()
//                    ,from.getSex(),from.getBirth(),from.getPt(),from.getMa());
//        }
//
//        return user;
//    }
}
