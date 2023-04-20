package com.sparta.spring_assignment_lv3.repository;

import com.sparta.spring_assignment_lv3.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserIdAndPassword(String userid, String password);

    Boolean existsByUserId(String userid);

    Optional<Users> findByUserId(String username);
}
