package com._520it.crm.service.impl;

import com._520it.crm.domain.Department;
import com._520it.crm.mapper.DepartmentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper dao;
    @Override
    public int save(Department dept) {
        return dao.insert(dept);
    }

    @Override
    public int update(Department dept) {
        return dao.updateByPrimaryKey(dept);
    }

    @Override
    public int delete(Long id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public Department get(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return dao.selectAll();
    }

    @Override
    public PageResult selectByCondition(QueryObject qo) {
/*        Long count = dao.queryByConditionCount(qo);
        if (count > 0){
            List<Employee> result = dao.queryByCondition(qo);
            return new PageResult(count,result);
        }else {
            return PageResult.EMPTY;
        }*/
        return null;
    }

}
