package com.ghy.server.generator.mapper;

import com.ghy.server.generator.domain.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
* @author aaa
* @description 针对表【department】的数据库操作Mapper
* @createDate 2023-12-11 12:14:19
* @Entity generator.domain.Department
*/
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("SELECT DISTINCT department_name FROM department")
    List<String> sortOptions();
}




