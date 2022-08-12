package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;
public interface IEmployeeService {

    int save(Employee emp);
    int update(Employee emp);
    int delete(Long id);
    Employee get(Long id);
    List<Employee> selectAll();
    PageResult selectByCondition(QueryObject qo);

    Employee queryByLogin(String username, String password);

    int updateState(Long id);

    List<Long> queryByRoleIdByEid(Long eid);

    PageResult selectMangerList();

    /*根据潜在客户查询负责人*/
    List<Employee> selectByPotential();
    /*根据正式客户查询负责人*/
    List<Employee> selectByCustomer();

    // 查询用户根据客户负责人的Id
    List<Employee> selectByInCharge();

    PageResult sellerList();
}
