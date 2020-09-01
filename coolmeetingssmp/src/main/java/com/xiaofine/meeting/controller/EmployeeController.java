package com.xiaofine.meeting.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: xiaofine
 */
@Controller
@RequestMapping("/admin")
public class EmployeeController {

    public static final Integer PAGE_SIZE =10;

    @Autowired
    EmployeeService employeeService;

//    分页
    @RequestMapping("/searchemployees")
    public String getAllEmployees(Employee employee, @RequestParam(defaultValue = "1") Integer page, Model model){
        LambdaQueryChainWrapper<Employee> wrapper = employeeService.lambdaQuery();
        if(employee.getEmployeename()!=null && employee.getEmployeename().length()>0){
            wrapper.eq(Employee::getEmployeename,employee.getEmployeename());
        }
        if(employee.getUsername()!=null && employee.getUsername().length()>0){
            wrapper.eq(Employee::getUsername,employee.getUsername());
        }
        wrapper.eq(Employee::getStatus,employee.getStatus());

        IPage<Employee> iPage = employeeService.page(new Page<Employee>(page,PAGE_SIZE),wrapper.getWrapper());
        List<Employee> employees =iPage.getRecords();
        Integer total = employeeService.count(wrapper.getWrapper());
        model.addAttribute("emps",employees);
        model.addAttribute("total",total);
        model.addAttribute("page",page);
        model.addAttribute("pagenum",total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);
        return "searchemployees";
    }

    @RequestMapping("updateemp")
    public String updateemp(Integer id){
        Boolean result = employeeService.lambdaUpdate().eq(Employee::getEmployeeid,id).set(Employee::getStatus,2).update();
        System.out.println(result);
        return "redirect:/admin/searchemployees?status=1";
    }
}
