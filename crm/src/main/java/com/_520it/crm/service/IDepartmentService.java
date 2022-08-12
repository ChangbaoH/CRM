package com._520it.crm.service;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IDepartmentService {

    int save(Department dept);
    int update(Department dept);
    int delete(Long id);
    Department get(Long id);
    List<Department> selectAll();
    PageResult selectByCondition(QueryObject qo);
}
