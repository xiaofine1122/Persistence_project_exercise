package com.xiaofine.meeting.controller;

import com.xiaofine.meeting.model.MeetingRoom;
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
        model.addAttribute("mrs",meetingRoomService.list());
        return "meetingrooms";
    }

    @RequestMapping("/roomdetails")
    public String roomdetails(Integer roomid, Model model){
        model.addAttribute("mr",meetingRoomService.getById(roomid));
        return "roomdetails";
    }

    @RequestMapping("/updateroom")
    public String updateroom(MeetingRoom meetingRoom){
        Boolean result = meetingRoomService.saveOrUpdate(meetingRoom);
        if (result ) {
            return "redirect:/meetingrooms";
        }else {
            return "forward:/roomdetails";
        }
    }

    @RequestMapping("/admin/addmeetingroom")
    public String addmeetingroom(){
        return "addmeetingroom";
    }

    @RequestMapping("/admin/doAddMr")
    public String  doAddMr(MeetingRoom meetingRoom){
        Boolean result = meetingRoomService.save(meetingRoom);
        return "redirect:/meetingrooms";
    }




}
