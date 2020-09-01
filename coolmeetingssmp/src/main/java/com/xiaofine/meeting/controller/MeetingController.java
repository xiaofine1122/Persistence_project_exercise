package com.xiaofine.meeting.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xiaofine.meeting.model.Department;
import com.xiaofine.meeting.model.Employee;
import com.xiaofine.meeting.model.Meeting;
import com.xiaofine.meeting.model.MeetingParticipants;
import com.xiaofine.meeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    MeetingParticipantsService meetingParticipantsService;

    @RequestMapping("bookmeeting")
    public String bookmeeting(Model model){
        model.addAttribute("mrs",meetingRoomService.list());
        return "bookmeeting";
    }

    @RequestMapping("/alldeps")
    @ResponseBody
    public List<Department> getAllDeps() {
        return departmentService.list();
    }
    @RequestMapping("/getempbydepid")
    @ResponseBody
    public List<Employee> getEmpsByDepId(Integer depId) {
        LambdaQueryChainWrapper<Employee> wrapper = employeeService.lambdaQuery();
        wrapper.eq(Employee::getDepartmentid,depId);
        return employeeService.list(wrapper.getWrapper());
    }

//    批量保存
    @RequestMapping("/doAddMeeting")
    public String doAddMeeting(Meeting meeting, Integer[] mps, HttpSession session) {
        Employee currentuser = (Employee) session.getAttribute("currentuser");
        meeting.setReservationistid(currentuser.getEmployeeid());

        Boolean result = meetingService.save(meeting);
        List<MeetingParticipants> mpps = new ArrayList<>();
        if(mps!=null && mps.length>0){
            for (Integer eid:mps
            ) {
                MeetingParticipants mpp = new MeetingParticipants();
                mpp.setMeetingid(meeting.getMeetingid());
            mpp.setEmployeeid(eid);
            mpps.add(mpp);
        }
        }
        Boolean result1 = true;
        if(mpps.size()>0){
            result1 = meetingParticipantsService.saveBatch(mpps);
        }
        if (result && result1) {
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
