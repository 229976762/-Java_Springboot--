package com.ghy.server.generator.control;

import com.ghy.exceptionHandler.MyException;
import com.ghy.form.*;
import com.ghy.result.Result;
import com.ghy.server.generator.service.AssetService;
import com.ghy.server.generator.service.EmployeeService;
import com.ghy.server.response.AssetCategoryQueryResponse;
import com.ghy.server.response.AssetQueryResponse;
import com.ghy.server.response.EmployessQueryResponse;
import com.ghy.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author GHY
 * @date 2023/11/21
 */

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AssetService assetService;

    /**
     * 登录校验
     * @param loginForm
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm){
        log.info("登录校验：{}",loginForm);
        return Result.success(JwtUtil.createJWT());
    }


    /**
     * 修改密码
     * @param ruleForm
     * @return
     */
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePwFrom ruleForm) throws MyException {
        log.info("更改密码");

        employeeService.changePassword(ruleForm);

        return Result.success();
    }

    /**
     * 获取资产列表
     * @return
     */
    @PostMapping("/getAssetList")
    public Result getAssetList(@RequestBody ListQuery listQuery) {
        log.info("获取资产列表:{}");

        AssetQueryResponse response = assetService.getAssetList(listQuery);
        return Result.success(response);
    }


    /**
     * 获取资产分类
     * @return
     */
    @PostMapping("/assetCategoryList")
    public Result assetCategoryList(@RequestBody CategoryListQuery query) {
        log.info("获取资产分类",query);

        AssetCategoryQueryResponse response = assetService.assetCategoryList(query);
        return Result.success(response);
    }

    /**
     * 获取员工列表
     * @return
     */
    @PostMapping("/getEmplyoessList")
    public Result getEmplyoessList(@RequestBody EmployeesListQuery listQuery) {
        log.info("获取员工列表:{}",listQuery);

        EmployessQueryResponse response = employeeService.getEmployeesList(listQuery);

        return Result.success(response);
    }


    /**
     * 个人信息
     * @param id
     * @return
     */
    @GetMapping("/selfInfo")
    public Result selfInfo(@RequestParam(name = "id") Long id) {
        log.info("查看个人信息：{}{}",id);

        EmployeesListQuery query = employeeService.selfInfo(id);

        return Result.success(query);
    }


    /**
     * 修改个人信息
     * @param selfInfoFrom
     * @return
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody SelfInfoFrom selfInfoFrom){
        log.info("修改个人信息：{}",selfInfoFrom);
        EmployeesListQuery query = employeeService.updateInfo(selfInfoFrom);

        return Result.success(query);
    }
}
