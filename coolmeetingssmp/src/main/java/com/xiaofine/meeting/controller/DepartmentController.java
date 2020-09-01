package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.model.Department;
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
        Department dept = departmentService.getById(id);
        System.out.println("dept"+dept);
    }

    @RequestMapping("/departments")
    public String departments(Model model){
        model.addAttribute("deps", departmentService.list());
        return "departments";
    }

    @RequestMapping("/adddepartment")
    public String adddepartment(String departmentname){
        Department department =new Department();
        department.setDepartmentname(departmentname);
        Boolean result = departmentService.save(department);
        System.out.println(result);
        return "redirect:departments";
    }

    @RequestMapping("deletedep")
    public String deletedep(Integer departmentid){
        departmentService.removeById(departmentid);
        return "redirect:/admin/departments";
    }

    @RequestMapping("updatedep")
    @ResponseBody
    public String updatedep(Integer id,String name){
        Boolean result = departmentService.lambdaUpdate().eq(Department::getDepartmentid,id).set(Department::getDepartmentname,name).update();
        System.out.println(result);
        if(result){
            return "success";
        }
        return "error";

    }

}
