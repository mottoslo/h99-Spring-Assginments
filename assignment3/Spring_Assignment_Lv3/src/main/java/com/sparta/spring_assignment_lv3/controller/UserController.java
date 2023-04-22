package com.sparta.spring_assignment_lv3.controller;


import com.sparta.spring_assignment_lv3.dto.LoginRequestDto;
import com.sparta.spring_assignment_lv3.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv3.service.UserService;
import com.sparta.spring_assignment_lv3.utils.Exceptions.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginRequest(@RequestBody LoginRequestDto loginRequestDto){
        HttpHeaders headers = userService.verifyUser(loginRequestDto);
        return new ResponseEntity<>("로그인 성공!", headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public String registerRequest(@RequestBody RegisterRequestDto registerRequestDto){
        return userService.register(registerRequestDto);
    }

}
