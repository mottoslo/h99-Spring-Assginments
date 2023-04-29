package com.sparta.spring_assignment_lv4.service;


import com.sparta.spring_assignment_lv4.repository.UserRepository;
import com.sparta.spring_assignment_lv4.dto.SignupRequestDto;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.utils.Exceptions.AdminTokenInvalidException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.UserIdExistsException;
import com.sparta.spring_assignment_lv4.utils.JwtUtil;
import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String ADMIN_TOKEN = "admin123";

    @Transactional
    public void signupRequest(SignupRequestDto requestDto) {
        User new_user;
        if (userRepository.existsByUserId(requestDto.getUsername())) {
            throw new UserIdExistsException("이미 존재하는 ID입니다");
        } else if(requestDto.isAdmin()){
            if(!requestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new AdminTokenInvalidException("AdminToken 값이 올바르지 않습니다.");
            }
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        new_user = new User(requestDto);
        userRepository.save(new_user);
    }

//    @Transactional
//    public void loginRequest(UserDetailsImpl userDetails, HttpServletResponse response) {
//        String token = jwtUtil.createToken(userDetails);
//        response.addHeader(jwtUtil.AUTHORIZATION_HEADER,token);
//    }
}
