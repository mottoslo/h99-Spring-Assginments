package com.sparta.spring_assignment_lv4.EventListener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        // 인증 정보가 올바르게 생성되었는지 로그로 확인합니다.
        System.out.println(authentication.getName() + " logged in successfully.");
    }
}
