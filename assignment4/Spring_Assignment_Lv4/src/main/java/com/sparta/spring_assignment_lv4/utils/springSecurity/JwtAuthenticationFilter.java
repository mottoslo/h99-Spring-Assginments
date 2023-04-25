package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.Repository.UserRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.MethodNotAllowedException;
import com.sparta.spring_assignment_lv4.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final boolean postOnly = false;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new MethodNotAllowedException("Authentication method not supported : " + request.getMethod());
        }
        String username = obtainUsername(request);
        username = (username != null) ? username.trim() : "";
        String password = obtainPassword(request);
        password = (password != null) ? password : "";



//        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
//                password);
        UsernamePasswordAuthenticationToken auth = createAuthentication(request);
        return this.getAuthenticationManager().authenticate(auth);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(){

    }
}
