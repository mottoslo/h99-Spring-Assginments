package com.sparta.spring_assignment_lv2.service;

import com.sparta.spring_assignment_lv2.dto.LoginRequestDto;
import com.sparta.spring_assignment_lv2.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv2.jwt.JwtUtil;
import com.sparta.spring_assignment_lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String verifyUser(LoginRequestDto loginRequestDto, HttpServletResponse response) {


    }

    public String register(RegisterRequestDto registerRequestDto) {
    }
}
