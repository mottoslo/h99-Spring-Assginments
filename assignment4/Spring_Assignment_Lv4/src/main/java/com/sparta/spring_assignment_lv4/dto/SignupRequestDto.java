package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    private String name;
    private String username;
    private String password;
    private boolean admin;
    private String adminToken;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(
                this.name,
                this.username,
                passwordEncoder.encode(this.password),
                this.admin
        );
    }
}
