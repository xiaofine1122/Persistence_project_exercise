package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.pojo.Employee;
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
        List<Employee> employees = employeeService.getEmpsByStatus(PENDING_APPROVE);
        model.addAttribute("emps",employees);
        return "approveaccount";
    }

    @RequestMapping("/updatestatus")
    public String updatestatus(Integer employeeid,Integer status){
        employeeService.updateStatus(employeeid,status);
        return "redirect:/admin/approveaccount";
    }



}
