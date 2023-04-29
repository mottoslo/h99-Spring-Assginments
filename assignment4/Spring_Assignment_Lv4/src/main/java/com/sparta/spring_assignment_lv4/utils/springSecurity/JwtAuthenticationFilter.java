package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //헤더 확인
        if(!hasBearer(request)){
            filterChain.doFilter(request, response);
            return;
        }
        //인증로직 시작
        String token = jwtUtil.resolveToken(request);  // 접두사 빼고 token만 가져오기
        if(!jwtUtil.validateToken(token)){ // Exception은 JwtUtil에 구현
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(token);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String token) {
        UserDetailsImpl userDetails = getUserDetail(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, userDetails.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private UserDetailsImpl getUserDetail(String token){
        Claims userInfo = jwtUtil.getUserInfoFromToken(token);
        String username = userInfo.getSubject();
        return userDetailsService.loadUserByUsername(username);
    }
    private boolean hasBearer(HttpServletRequest request){
        String header = request.getHeader(jwtUtil.AUTHORIZATION_HEADER);
        if(ObjectUtils.isEmpty(header) || !header.startsWith(jwtUtil.BEARER_PREFIX)){
            return false;
        }
        else return true;
    }
}


