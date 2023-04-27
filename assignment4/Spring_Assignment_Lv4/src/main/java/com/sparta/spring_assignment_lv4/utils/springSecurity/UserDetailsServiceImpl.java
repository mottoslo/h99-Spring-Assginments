package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.repository.UserRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("내 UserDetailsserviceImpl 실행됨");
        User user = userRepository.findByUserId(username).orElseThrow(
                () -> new UserNotFoundException("유저를 찾을 수 없습니다")
        );
        return new UserDetailsImpl(user);
    }
}
