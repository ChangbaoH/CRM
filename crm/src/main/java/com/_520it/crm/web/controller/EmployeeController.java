package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Menu;
import com._520it.crm.domain.Permission;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IMenuService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.PermissionUtils;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IMenuService menuService;
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {
        UserContext.setLocalThread(request);
        AjaxResult result = null;
        Employee user = employeeService.queryByLogin(username, password);
        if (user != null) {
            // 把当前用户放到session中
            request.getSession().setAttribute(UserContext.USER_IN_SESSION, user);
            // 查询该用户拥有的权限,并放到session中
            List<Permission> permissions = permissionService.queryPermissionByEid(user.getId());
            request.getSession().setAttribute(UserContext.PERMISSION_IN_SESSION, permissions);

            List<Menu> userMenu = menuService.queryMenu();
            PermissionUtils.getMenuForPermission(userMenu);
            request.getSession().setAttribute(UserContext.MENU_IN_SESSION,userMenu);
            result = new AjaxResult(true, "登陆成功");
        } else {
            result = new AjaxResult("账号密码有误");
        }
        return result;
    }


    @RequestMapping("/employee")
    public String index() {
        return "employee";
    }


    @RequestMapping("/employee_queryRoleIdByEid")
    @ResponseBody
    public List<Long> getRoleId(Long eid) {
        return employeeService.queryByRoleIdByEid(eid);
    }

    @ModelAttribute
    public void before(Long id, Model model) {
        if (id != null) {
            //更新操作
            Employee emp = new Employee();
            emp.setPassword("1");
            model.addAttribute(emp);
        }
    }


    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject qo) {
        PageResult result = null;
        result = employeeService.selectByCondition(qo);
        return result;
    }


    @RequestMapping("/employee_save")
    @ResponseBody
    public AjaxResult save(Employee emp) {
        AjaxResult result = null;

        try {
            emp.setAdmin(false);
            emp.setInputtime(new Date());
            emp.setState(true);
            emp.setPassword("1");
            int effectCount = employeeService.save(emp);
            if (effectCount > 0) {
                result = new AjaxResult(true, "保存成功");
            } else {
                result = new AjaxResult("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存异常，请联系管理员");
        }
        return result;
    }


    @RequestMapping("/employee_update")
    @ResponseBody
    public AjaxResult update(Employee emp) {
        AjaxResult result = null;
        try {
            int effectCount = employeeService.update(emp);
            if (effectCount > 0) {
                result = new AjaxResult(true, "更新成功");
            } else {
                result = new AjaxResult("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("更新异常，请联系管理员");
        }
        return result;
    }


    @RequestMapping("/employee_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            int effectCount = employeeService.updateState(id);
            if (effectCount > 0) {
                result = new AjaxResult(true, "离职成功");
            } else {
                result = new AjaxResult("离职失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("离职异常，请联系管理员");
        }
        return result;
    }


    @RequestMapping("/manager_list")
    @ResponseBody
    public PageResult managerList() {
        PageResult result = null;
        result = employeeService.selectMangerList();

        return result;

    }

    @RequestMapping("/seller_list")
    @ResponseBody
    public PageResult sellerList() {
        PageResult result = null;
        result = employeeService.sellerList();
        return result;
    }

/*    *//*根据潜在客户查询负责人*//*
    @RequestMapping("/selectByPotential")
    @ResponseBody
    public List<Employee> selectByPotential() {
        return employeeService.selectByPotential();
    }

    *//*根据正式客户查询负责人*//*
    @RequestMapping("/selectByCustomer")
    @ResponseBody
    public List<Employee> selectByCustomer() {
        return employeeService.selectByCustomer();
    }


    *//*根据正式客户查询负责人*//*
    @RequestMapping("/incharge_list")
    @ResponseBody
    public List<Employee> selectByInCharge() {
        return employeeService.selectByInCharge();
    }


    @RequiredPermission("系统管理员")
    public void manager() {

    }*/

}
