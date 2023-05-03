package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.entity.Comment;
import com.sparta.spring_assignment_lv4.repository.QueryDSL.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
//    List<Comment> findByRootArticleId(Long rootArticleId);
}
