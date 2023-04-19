package com.sparta.spring_assignment_lv2.repository;

import com.sparta.spring_assignment_lv2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();

}
