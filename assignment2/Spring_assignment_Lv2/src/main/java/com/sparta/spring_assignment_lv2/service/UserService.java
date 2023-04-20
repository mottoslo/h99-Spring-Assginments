package com.sparta.spring_assignment_lv2.service;

import com.sparta.spring_assignment_lv2.dto.LoginRequestDto;
import com.sparta.spring_assignment_lv2.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv2.entity.Users;
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
            userRepository.findByUserIdAndPassword(
                    loginRequestDto.getUserid(),
                    loginRequestDto.getPassword()
            ).orElseThrow(
                    () -> new IllegalArgumentException("id나 pw가 일치하지 않습니다.")
            );

        String token = jwtUtil.createToken(loginRequestDto.getUserid());
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, token);
        return "로그인 성공 !";
    }

    public String register(RegisterRequestDto registerRequestDto) {
        if(getIdExists(registerRequestDto.getUserid())){
            return "이미 사용중인 ID입니다";
        }
        else{
            userRepository.save(new Users(registerRequestDto));
            return "회원가입에 성공하였습니다";
        }

    }

    private Boolean getIdExists(String userid) {
        return userRepository.existsByUserId(userid);
    }
}
