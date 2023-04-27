package com.sparta.spring_assignment_lv4.utils.springSecurity;

import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class UserDetailsImpl implements UserDetails {
        private final User user;
        private final String username;

    public UserDetailsImpl(User user) {
        this.user = user;
        this.username = user.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();
        String authority = role.getAuthorization();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("user.getPassword() = " + user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
