package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper dao;
    @Override
    public int save(Employee emp) {
        int r = dao.insert(emp);
        List<Role> roles = emp.getRoles();
        // 员工有角色时,保存角色的
        if (roles != null) {
            for (Role role : roles) {
                dao.handleRelation(emp.getId(), role.getId());
            }
        }
        return r;
    }

    @Override
    public int update(Employee emp) {
        List<Role> roles = emp.getRoles();
        // 员工有角色时,保存角色的
        if (roles != null) {
            // 删除原有的角色
            dao.deleteRoleByEid(emp.getId());
            // 新增角色
            for (Role role : roles) {
                dao.handleRelation(emp.getId(), role.getId());
            }
        }

        return dao.updateByPrimaryKey(emp);
    }

    @Override
    public int delete(Long id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public Employee get(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return dao.selectAll();
    }

    @Override
    public PageResult selectByCondition(QueryObject qo) {
        Long count = dao.queryByConditionCount(qo);
        if (count > 0){
            List<Employee> result = dao.queryByCondition(qo);
            return new PageResult(count,result);
        }else {
            return PageResult.EMPTY;
        }
    }

    @Override
    public Employee queryByLogin(String username, String password) {
        return dao.queryByLogin(username,password);
    }

    @Override
    public int updateState(Long id) {
        return dao.updateState(id);
    }

    /**
     * 根据员工的id,查询出对应的角色id数组
     *
     * @param eid 员工id
     * @return 返回对应角色数组
     */
    @Override
    public List<Long> queryByRoleIdByEid(Long eid) {
        return dao.queryByRoleIdByEid(eid);
    }

    @Override
    public PageResult selectMangerList() {

        Long total = dao.selectAllCount();
        List<Employee> results = dao.selectAll();

        return new PageResult(total, results);
    }


    @Override
    public List<Employee> selectByPotential() {
        return dao.selectByPotential();
    }

    @Override
    public List<Employee> selectByCustomer() {
        return dao.selectByCustomer();
    }

    @Override
    public List<Employee> selectByInCharge() {
        return dao.selectByInCharge();
    }

    public PageResult sellerList() {
        Long total = dao.selectAllCount();
        List<Employee> results = dao.selectAll();
        return new PageResult(total, results);
    }

}
