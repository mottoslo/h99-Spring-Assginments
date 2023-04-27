package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.utils.Exceptions.PasswordIncorrectException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class IdPwAuthenticationManager implements AuthenticationManager{
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("내 authenticationManager");
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        Authentication new_token;
        if(authentication.getCredentials().equals(userDetails.getPassword())){
            new_token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(), userDetails.getAuthorities());
        }
        else{
            throw new PasswordIncorrectException("비밀번호가 일치하지 않습니다");
        }

        return new_token;
    }
}
