package com.xiaofine.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: xiaofine
 */
@Controller
public class NotificationsController {

@GetMapping("/notifications")
public String notifications(){
        return "notifications";
        }
        }
