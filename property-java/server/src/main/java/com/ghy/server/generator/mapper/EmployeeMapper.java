package com.ghy.server.generator.mapper;

import com.ghy.form.EmployeesListQuery;
import com.ghy.form.SelfInfoFrom;
import com.ghy.server.generator.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
* @author aaa
* @description 针对表【employee】的数据库操作Mapper
* @createDate 2023-12-11 12:14:19
* @Entity generator.domain.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<EmployeesListQuery> getEmployeesList(EmployeesListQuery listQuery);

    void updateInfo(SelfInfoFrom selfInfoFrom);
}




