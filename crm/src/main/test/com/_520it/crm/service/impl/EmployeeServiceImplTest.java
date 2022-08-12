package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceImplTest {
    @Autowired
    IEmployeeService employeeService;
    @Test
    public void save() {
        Employee emp = new Employee();
        emp.setAdmin(true);
        emp.setEmail("123@qq.com");
        emp.setInputtime(new Date());
        emp.setPassword("666666");
        emp.setState(true);
        emp.setTel("12301234625");
        emp.setUsername("admin");
        emp.setRealname("超级管理员");
        employeeService.save(emp);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void selectByCondition() {
    }

    @Test
    public void queryByLogin() {
        Employee user = employeeService.queryByLogin("admin","1");
        System.out.println(user.getUsername());
    }
}