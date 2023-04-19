package com.sparta.spring_assignment_lv2.repository;

import com.sparta.spring_assignment_lv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
