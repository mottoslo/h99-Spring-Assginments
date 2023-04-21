package com.sparta.spring_assignment_lv3.repository;

import com.sparta.spring_assignment_lv3.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
}
