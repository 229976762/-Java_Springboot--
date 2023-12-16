package com.ghy.server.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghy.exceptionHandler.MyException;
import com.ghy.exceptionHandler.RCode;
import com.ghy.form.ChangePwFrom;
import com.ghy.form.EmployeesListQuery;
import com.ghy.form.SelfInfoFrom;
import com.ghy.server.generator.domain.Asset;
import com.ghy.server.generator.domain.Department;
import com.ghy.server.generator.domain.Employee;
import com.ghy.server.generator.mapper.AssetMapper;
import com.ghy.server.generator.mapper.DepartmentMapper;
import com.ghy.server.generator.service.EmployeeService;
import com.ghy.server.generator.mapper.EmployeeMapper;
import com.ghy.server.response.EmployessQueryResponse;
import com.ghy.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author aaa
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-12-11 12:14:19
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public EmployessQueryResponse getEmployeesList(EmployeesListQuery listQuery) {

        List<EmployeesListQuery> list = employeeMapper.getEmployeesList(listQuery);
        List<String> sortOptions = departmentMapper.sortOptions();

        // 计算起始索引和结束索引
        int startIndex = (listQuery.getPage() - 1) * listQuery.getLimit();
        int endIndex = Math.min(startIndex + listQuery.getLimit(), list.size());

        List<EmployeesListQuery> pageData = list.subList(startIndex, endIndex);


        return new EmployessQueryResponse(pageData, (long) list.size(),sortOptions);
    }

    @Override
    public EmployessQueryResponse addEmployees(EmployeesListQuery listQuery) throws MyException {

        Employee employee = employeeMapper.selectById(listQuery.getEmployeeId());
        if(employee != null)
            throw new MyException(RCode.EMPLOYEES_ADD_ERROR);

        QueryWrapper<Department> query1 = Wrappers.query();
        query1.eq("department_name",listQuery.getDepartmentName());
        Department department = departmentMapper.selectOne(query1);

        employee = new Employee();
        BeanUtils.copyProperties(listQuery,employee);
        employee.setDepartmentId(department.getDepartmentId());

        employeeMapper.insert(employee);

        EmployeesListQuery query = new EmployeesListQuery();
        query.setPage(listQuery.getPage());
        query.setLimit(listQuery.getLimit());
        return getEmployeesList(query);
    }

    @Override
    public EmployessQueryResponse updateEmployees(Long id, EmployeesListQuery listQuery) throws MyException {

        Employee employee = employeeMapper.selectById(listQuery.getEmployeeId());

        if(employee != null && employee.getEmployeeId() != id.longValue())
            throw new MyException(RCode.EMPLOYEES_ADD_ERROR);

        List<Asset> assetList = assetMapper.selectEmployees(id);
        List<Long> asset = null;
        if(assetList != null && assetList.size() != 0)
            asset =  assetList .stream().map(Asset::getAssetId).collect(Collectors.toList());
        assetMapper.setGuardianIdNull(id);
        employeeMapper.deleteById(id);
        EmployessQueryResponse response = addEmployees(listQuery);

        if(asset != null && asset.size() != 0)
            assetMapper.recoverGuardianId(asset,listQuery.getEmployeeId());

        return response;
    }

    @Override
    public EmployessQueryResponse deleteEmployees(Long id) {

        Employee employee = employeeMapper.selectById(id);
        if(employee.getIsManager() == 1){
            List<Long> asset = null;
            List<Asset> assetList = assetMapper.selectEmployees(id);
            if(assetList != null && assetList.size() != 0)
                asset =  assetList .stream().map(Asset::getAssetId).collect(Collectors.toList());
            assetMapper.setGuardianIdNull(id);
        }

        employeeMapper.deleteById(id);


        EmployeesListQuery query = new EmployeesListQuery();
        query.setPage(1);
        query.setLimit(10);
        return getEmployeesList(query);
    }

    @Override
    public EmployeesListQuery selfInfo(Long id) {

        Employee employee = employeeMapper.selectById(id);

        Department department = departmentMapper.selectById(employee.getDepartmentId());

        EmployeesListQuery query = new EmployeesListQuery();

        BeanUtils.copyProperties(employee,query);
        query.setDepartmentName(department.getDepartmentName());
        return query;
    }

    @Override
    public EmployeesListQuery updateInfo(SelfInfoFrom selfInfoFrom) {

        employeeMapper.updateInfo(selfInfoFrom);

        return selfInfo(selfInfoFrom.getEmployeeId());
    }

    @Override
    public void changePassword(ChangePwFrom ruleForm) throws MyException {
        Employee employee = employeeMapper.selectById(ruleForm.getId());

        if(!employee.getPassword().equals(MD5Util.md5(ruleForm.getOldPass())))
            throw new MyException(RCode.UPDATE_PASS_ERROR);

        employee.setPassword(MD5Util.md5(ruleForm.getPass()));

        employeeMapper.updateById(employee);

    }

}




