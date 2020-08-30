package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.model.Department;
import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.model.Meeting;
import com.xiaofine.meeting.service.DepartmentService;
import com.xiaofine.meeting.service.EmployeeService;
import com.xiaofine.meeting.service.MeetingRoomService;
import com.xiaofine.meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: xiaofine
 */
@Controller
public class MeetingController {

    @Autowired
    MeetingRoomService meetingRoomService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    MeetingService meetingService;
    @RequestMapping("bookmeeting")
    public String bookmeeting(Model model){
        model.addAttribute("mrs",meetingRoomService.getAllMeetRoom());
        return "bookmeeting";
    }

    @RequestMapping("/alldeps")
    @ResponseBody
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }
    @RequestMapping("/getempbydepid")
    @ResponseBody
    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeService.getEmpsByDepId(depId);
    }

    @RequestMapping("/doAddMeeting")
    public String doAddMeeting(Meeting meeting, Integer[] mps, HttpSession session) {
        Employee currentuser = (Employee) session.getAttribute("currentuser");
        meeting.setReservationistid(currentuser.getEmployeeid());
        Integer result = meetingService.addMeeting(meeting,mps);
        if (result == 1) {
            return "redirect:/searchmeetings";
        }else{
            return "forward:/bookmeeting";
        }
    }
    @RequestMapping("/searchmeetings")
    public String searchmeetings() {
        return "searchmeetings";
    }





}
