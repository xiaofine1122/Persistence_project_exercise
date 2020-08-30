package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.model.Department;
import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.service.DepartmentService;
import com.xiaofine.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: xiaofine
 */

@Controller
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model, HttpSession httpSession){
        Employee employee =employeeService.doLogin(username,password);
        if(employee == null || !employee.getPassword().equals(password)){
            model.addAttribute("error", "用户名或密码输入错误，登录失败");
            return "forward:/";
        }else {
            if (employee.getStatus() == 0){
                model.addAttribute("error","用户待审批");
                return "forward:/";
            }else if (employee.getStatus() == 2) {
                model.addAttribute("error", "用户审批未通过");
                return "forward:/";
            } else {
                httpSession.setAttribute("currentuser",employee);
                return "redirect:/notifications";
            }
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        List<Department> deps = departmentService.getAllDeps();
        model.addAttribute("deps",deps);
        return "register";
    }

    @PostMapping("/doReg")
    public String doReg(Employee employee,Model model){
        Integer result =  employeeService.doReg(employee);
        if(result == 1){
            return "redirect:/";
        }else {
            model.addAttribute("error","注册失败");
            model.addAttribute("employee",employee);
            return "forward:register";
        }
    }

}
