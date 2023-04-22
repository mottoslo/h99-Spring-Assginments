package com.sparta.spring_assignment_lv3.service;

import com.sparta.spring_assignment_lv3.dto.LoginRequestDto;
import com.sparta.spring_assignment_lv3.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv3.entity.Users;
import com.sparta.spring_assignment_lv3.utils.Exceptions.MyException;
import com.sparta.spring_assignment_lv3.utils.jwt.JwtUtil;
import com.sparta.spring_assignment_lv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final String adminKey = "admintest";

    public HttpHeaders verifyUser(LoginRequestDto loginRequestDto){

        Users user = userRepository.findByUserIdAndPassword(
                loginRequestDto.getUserid(),
                loginRequestDto.getPassword()
        ).orElseThrow(
                () -> new NullPointerException("유저를 찾을 수 없습니다.")
        );

        String token = jwtUtil.createToken(user.getUserId());
        HttpHeaders header = new HttpHeaders();
        header.add(jwtUtil.AUTHORIZATION_HEADER, token);
        return header;
    }

    public String register(RegisterRequestDto registerRequestDto) {

        checkRegisterRequest(registerRequestDto);

        userRepository.save(new Users(registerRequestDto));
        return "회원가입에 성공하였습니다";

    }

    private boolean checkIdPolicy(String id){return id.matches("^[a-z0-9]{4,10}$");}
    private boolean checkPwPolicy(String id){return id.matches("^[a-zA-Z0-9]{10,15}$");}
    private boolean getIdExists(String userid) { return userRepository.existsByUserId(userid);}
    private void checkRegisterRequest(RegisterRequestDto requestDto){
        if(!checkIdPolicy(requestDto.getUserid())){
            throw new IllegalArgumentException("Id 형식이 올바르지 않습니다.");
        }
        if(!checkPwPolicy(requestDto.getPassword())){
            throw new IllegalArgumentException("Pw 형식이 올바르지 않습니다");
        }
        if(getIdExists(requestDto.getUserid())){
            throw new IllegalArgumentException("이미 사용중인 ID입니다");
        }
        if(requestDto.isIsadmin()){
            if(!requestDto.getAdminkey().equals(adminKey))
                throw new IllegalArgumentException("관리자 Key가 일치하지 않습니다");
        }
    }

}
