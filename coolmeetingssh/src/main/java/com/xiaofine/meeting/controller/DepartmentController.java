package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.pojo.Department;
import com.xiaofine.meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: xiaofine
 */
@Controller
@RequestMapping("admin")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dep")
    public void getDeptById(Integer id){
        Department dept = departmentService.findById(id);
        System.out.println("dept"+dept);
    }

    @RequestMapping("/departments")
    public String departments(Model model){
        model.addAttribute("deps", departmentService.findAll());
        return "departments";
    }

    @RequestMapping("/adddepartment")
    public String adddepartment(String departmentname){
        Department department = new Department();
        department.setDepartmentname(departmentname);
        departmentService.save(department);
        return "redirect:departments";
    }

    @RequestMapping("deletedep")
    public String deletedep(Integer departmentid){
        departmentService.delete(departmentid);
        return "redirect:/admin/departments";
    }

    @RequestMapping("updatedep")
    @ResponseBody
    public String updatedep(Integer id,String name){
        Department department = departmentService.findById(id);
        department.setDepartmentname(name);
        departmentService.update(department);
        return "success";
//        if(result == 1){
//            return "success";
//        }
//        return "error";

    }

}
