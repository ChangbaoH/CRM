package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
@Controller
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/department_queryForEmp")
    @ResponseBody
    public List queryForEmp() {
        List<Department> result = departmentService.selectAll();
 
        return result;

    }

/*    @RequestMapping("/department")
    public String index() {
        System.out.println("IndexController.employeeList");
        return "department";
    }

    @RequiredPermission("部门查询")
    @RequestMapping("/department_list")
    @ResponseBody
    public PageResult list(DepartmentQueryObject qo) {
        PageResult result = null;
        try {
            result = departmentService.queryByCondition(qo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/department_queryForParent")
    @ResponseBody
    public List<Department> queryForParent() {
        List<Department> result = departmentService.listAll();

        return result;
    }

    @RequestMapping("/department_save")
    @ResponseBody
    public AjaxResult save(Department dept) {
        AjaxResult result = null;
        try {
            int effectCount = departmentService.save(dept);
            if (effectCount > 0) {
                result = new AjaxResult(true, "保存成功");
            } else {
                result = new AjaxResult("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常");
        }
        return result;
    }

    @RequestMapping("/department_update")
    @ResponseBody
    public AjaxResult update(Department dept) {
        AjaxResult result = null;
        try {
            dept.setState(true);
            int effectCount = departmentService.update(dept);
            if (effectCount > 0) {
                result = new AjaxResult(true, "更新成功");
            } else {
                result = new AjaxResult("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常");
        }
        return result;
    }

    @RequestMapping("/department_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {

            int effectCount = departmentService.delete(id);
            if (effectCount > 0) {
                result = new AjaxResult(true, "删除成功");
            } else {
                result = new AjaxResult("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("删除异常");
        }
        return result;
    }

    @RequestMapping("/department_menu")
    @ResponseBody
    public List<Department> queryDepartmentMenu() {
        return departmentService.queryDepartmentMenu();
    }*/
}
