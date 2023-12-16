package com.ghy.server.generator.service;

import com.ghy.exceptionHandler.MyException;
import com.ghy.form.ChangePwFrom;
import com.ghy.form.EmployeesListQuery;
import com.ghy.form.SelfInfoFrom;
import com.ghy.server.generator.domain.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghy.server.response.EmployessQueryResponse;

/**
* @author aaa
* @description 针对表【employee】的数据库操作Service
* @createDate 2023-12-11 12:14:19
*/
public interface EmployeeService extends IService<Employee> {

    EmployessQueryResponse getEmployeesList(EmployeesListQuery listQuery);

    EmployessQueryResponse addEmployees(EmployeesListQuery listQuery) throws MyException;

    EmployessQueryResponse updateEmployees(Long id, EmployeesListQuery listQuery) throws MyException;

    EmployessQueryResponse deleteEmployees(Long id);

    EmployeesListQuery selfInfo(Long id);

    EmployeesListQuery updateInfo(SelfInfoFrom selfInfoFrom);

    void changePassword(ChangePwFrom ruleForm) throws MyException;
}
