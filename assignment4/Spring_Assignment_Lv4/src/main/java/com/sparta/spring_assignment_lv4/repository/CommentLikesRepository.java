package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.entity.Comment;
import com.sparta.spring_assignment_lv4.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
    void deleteByCommentIdAndUserId(Long commentId, Long userId);
    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    List<CommentLikes> findAllByArticleIdAndUserId(Long articleId, Long userId);
}
