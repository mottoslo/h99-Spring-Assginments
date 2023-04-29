package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final AuthenticationSuccessHandler defaultHandler = new SavedRequestAwareAuthenticationSuccessHandler();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = jwtUtil.createToken(authentication.getName());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
//        System.out.println(response.isCommitted());
//        defaultHandler.onAuthenticationSuccess(request, response, authentication);  // 리디렉션 어케 처리할건지 고민해보자


    }
}
