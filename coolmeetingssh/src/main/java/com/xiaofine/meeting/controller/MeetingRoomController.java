package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.pojo.MeetingRoom;
import com.xiaofine.meeting.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiaofine
 */
@Controller
public class MeetingRoomController {

    @Autowired
    MeetingRoomService meetingRoomService;

    @RequestMapping("meetingrooms")
    public String meetingrooms(Model model){
        model.addAttribute("mrs",meetingRoomService.findAll());
        return "meetingrooms";
    }

    @RequestMapping("/roomdetails")
    public String roomdetails(Integer roomid, Model model){
        model.addAttribute("mr",meetingRoomService.findById(roomid));
        return "roomdetails";
    }

    @RequestMapping("/updateroom")
    public String updateroom(MeetingRoom meetingRoom){
        meetingRoomService.update(meetingRoom);
        return "redirect:/meetingrooms";
    }

    @RequestMapping("/admin/addmeetingroom")
    public String addmeetingroom(){
        return "addmeetingroom";
    }

    @RequestMapping("/admin/doAddMr")
    public String  doAddMr(MeetingRoom meetingRoom){
        meetingRoomService.save(meetingRoom);
        return "redirect:/meetingrooms";
    }




}
