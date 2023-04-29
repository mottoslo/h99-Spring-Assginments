package com.sparta.spring_assignment_lv4.service;


import com.sparta.spring_assignment_lv4.dto.ArticleDetailResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticleListResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticlePostRequestDto;
import com.sparta.spring_assignment_lv4.entity.Article;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.repository.ArticleRepository;
import com.sparta.spring_assignment_lv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    public ArticleDetailResponseDto postArticle(User user, ArticlePostRequestDto requestDto) {
        Article new_article = requestDto.toArticle(user);
        articleRepository.save(new_article);
        return new ArticleDetailResponseDto(new_article);
    }

    public List<ArticleListResponseDto> getArticleList() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }
}
