package com.sparta.spring_assignment_lv3.service;

import com.sparta.spring_assignment_lv3.dto.LoginRequestDto;
import com.sparta.spring_assignment_lv3.dto.RegisterRequestDto;
import com.sparta.spring_assignment_lv3.entity.Users;
import com.sparta.spring_assignment_lv3.jwt.JwtUtil;
import com.sparta.spring_assignment_lv3.repository.UserRepository;
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
        try{checkRegisterRequest(registerRequestDto);}
        catch(Exception e){return e.getMessage();}

        if(getIdExists(registerRequestDto.getUserid())){
            return "이미 사용중인 ID입니다";
        }
        else{
            userRepository.save(new Users(registerRequestDto));
            return "회원가입에 성공하였습니다";
        }
    }

    private boolean checkIdPolicy(String id){return id.matches("^[a-z0-9]{4,10}$");}
    private boolean checkPwPolicy(String id){return id.matches("^[a-zA-Z0-9]{10,15}$");}
    private void checkRegisterRequest(RegisterRequestDto registerRequestDto){
        if(!checkIdPolicy(registerRequestDto.getUserid())){
            throw new IllegalArgumentException("Id 형식이 올바르지 않습니다");
        }
        if(!checkPwPolicy(registerRequestDto.getPassword())){
            throw new IllegalArgumentException("Pw 형식이 올바르지 않습니다");
        }
    }
    private boolean getIdExists(String userid) {
        return userRepository.existsByUserId(userid);
    }

}
