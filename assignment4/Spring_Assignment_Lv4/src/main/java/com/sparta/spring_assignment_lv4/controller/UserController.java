package com.sparta.spring_assignment_lv4.controller;

import com.sparta.spring_assignment_lv4.dto.SignupRequestDto;
import com.sparta.spring_assignment_lv4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/login-page")
    public String getLoginPage(){ return "login"; }

    @GetMapping("/signup")
    public ModelAndView getSignupPage() { return new ModelAndView("signup"); }

    @PostMapping(value = "/signup")
    public String signupRequest(SignupRequestDto requestDto){
        userService.signupRequest(requestDto);
        return "redirect:/api/user/login-page";
    }
}
