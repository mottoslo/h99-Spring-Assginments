package com.sparta.spring_assignment_lv4.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        return new ModelAndView("login");
    }

//    @ResponseBody
//    @GetMapping("/printuserinfo")
//    public String printUserInfo(@AuthenticationPrincipal User user){
//        System.out.println("userID = " + userID);
//
//        return userID;
//    }
}
