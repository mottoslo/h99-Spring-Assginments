package com.sparta.spring_assignment_lv4.repository;

import com.sparta.spring_assignment_lv4.entity.ArticleLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikesRepository extends JpaRepository<ArticleLikes, Long> {
    void deleteByArticleIdAndUserId(Long articleId, Long userId);
    Boolean existsByArticleIdAndUserId(Long ArticleId, Long userId);

}
