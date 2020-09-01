package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: xiaofine
 */
@Controller
@RequestMapping("/admin")
public class ApproveaccountController {

    private static final Integer PENDING_APPROVE =0;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/approveaccount")
    public String approveaccount(Model model){
        List<Employee> employees = employeeService.lambdaQuery().eq(Employee::getStatus,PENDING_APPROVE).list();
        model.addAttribute("emps",employees);
        return "approveaccount";
    }

    @RequestMapping("/updatestatus")
    public String updatestatus(Integer employeeid,Integer status){
        Boolean result = employeeService.lambdaUpdate().eq(Employee::getEmployeeid,employeeid).set(Employee::getStatus,status).update();
        System.out.println(result);
        return "redirect:/admin/approveaccount";
    }



}
