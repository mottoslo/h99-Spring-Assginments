package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
    void deleteByCommentIdAndUserId(Long commentId, Long userId);
    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);
}
