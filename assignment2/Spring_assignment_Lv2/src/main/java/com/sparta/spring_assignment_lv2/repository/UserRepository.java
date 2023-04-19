package com.sparta.spring_assignment_lv2.repository;

import com.sparta.spring_assignment_lv2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    public Boolean existsByUserid(String userid);
}
