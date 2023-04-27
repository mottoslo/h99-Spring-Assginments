package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userid);

    boolean existsByUserId(String userId);
}
