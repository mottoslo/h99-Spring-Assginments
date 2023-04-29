package com.sparta.spring_assignment_lv4.controller;

import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    //로그인 test용 == 나중에 지워야함
    @GetMapping("/testing")
    public String jwtauthtest(@AuthenticationPrincipal UserDetailsImpl details, HttpServletResponse response){
        System.out.println("(details == null) = " + (details == null));
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("context.getAuthentication().isAuthenticated() = " + context.getAuthentication().isAuthenticated());

        System.out.println("context.getAuthentication().isAuthenticated() = " + context.getAuthentication().isAuthenticated());
        System.out.println("context.getAuthentication().getAuthorities() = " + context.getAuthentication().getAuthorities());
        System.out.println("context.getAuthentication().getPrincipal() = " + context.getAuthentication().getPrincipal());
        System.out.println("details.getUsername() = " + details.getUsername());
        return String.format("사용자명 : %s\n권한 : %s", details.getUsername(), context.getAuthentication().getAuthorities());

    }
}
