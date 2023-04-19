package com.sparta.spring_assignment_lv2.service;

import com.sparta.spring_assignment_lv2.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv2.entity.Users;
import com.sparta.spring_assignment_lv2.jwt.JwtUtil;
import com.sparta.spring_assignment_lv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

//    public String verifyUser(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//
//
//    }

    public String register(RegisterRequestDto registerRequestDto) {
        if(userRepository.existsByUserid(registerRequestDto.getUserid())){
            return "이미 사용중인 ID입니다";
        }
        else{
            userRepository.save(new Users(registerRequestDto));
            return "회원가입에 성공하였습니다";
        }

    }
}
