package com.ghy.server.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghy.server.generator.domain.Department;
import com.ghy.server.generator.service.DepartmentService;
import com.ghy.server.generator.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author aaa
* @description 针对表【department】的数据库操作Service实现
* @createDate 2023-12-11 12:14:19
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




