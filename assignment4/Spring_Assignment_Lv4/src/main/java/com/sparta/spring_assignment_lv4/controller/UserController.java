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

//    @PostMapping("/login")
//    public String loginRequest(@AuthenticationPrincipal UserDetails details, HttpServletResponse response){
////        userService.loginRequest(details, response);
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getAuthorities());
//        System.out.println(authentication.isAuthenticated());
//        System.out.println(details == null);
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            // 사용자가 인증되지 않은 경우, 로그인 페이지로 이동
//            return "login";
//        }
//
//        // 인증된 사용자 정보를 이용한 작업 수행
//        return "index";
//    }

    @GetMapping("/api/user/forbidden")
    public ModelAndView getForbiddenPage(){ return new ModelAndView("forbidden"); }
    @GetMapping("/signup")
    public ModelAndView getSignupPage() {return new ModelAndView("signup"); }


    @PostMapping(value = "/signup")
    public String signupRequest(SignupRequestDto requestDto){
        userService.signupRequest(requestDto);

        return "redirect:/api/login-page";

    }
}
