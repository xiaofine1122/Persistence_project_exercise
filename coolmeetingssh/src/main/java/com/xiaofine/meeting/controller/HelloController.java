package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("hello","hello 111");
        return "hello";
    }

    @GetMapping("/hell")
    public String hello1(){
        return employeeService.findAll().toString();
    }

}
