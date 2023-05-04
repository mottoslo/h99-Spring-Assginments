package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRootArticleIdAndRootCommentIdIsNull(Long rootArticleId);

    List<Comment> findByRootCommentId(Long rootCommentId);
}
