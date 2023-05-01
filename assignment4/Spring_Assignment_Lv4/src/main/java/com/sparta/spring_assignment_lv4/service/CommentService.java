package com.sparta.spring_assignment_lv4.service;

import com.sparta.spring_assignment_lv4.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Transactional
    public Article commentOnArticle(Long articleId) {

    }

    @Transactional
    public Article commentOnComment(Long articleId, Long commentId) {

    }
}
