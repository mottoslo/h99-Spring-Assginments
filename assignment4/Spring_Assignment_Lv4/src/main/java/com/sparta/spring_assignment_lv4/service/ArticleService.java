package com.sparta.spring_assignment_lv4.service;


import com.sparta.spring_assignment_lv4.dto.ArticleDetailResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticleListResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticlePostRequestDto;
import com.sparta.spring_assignment_lv4.entity.Article;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.repository.ArticleRepository;
import com.sparta.spring_assignment_lv4.repository.UserRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.ArticleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ArticleDetailResponseDto postArticle(User user, ArticlePostRequestDto requestDto) {
        Article new_article = requestDto.toArticle(user);
        articleRepository.save(new_article);
        return new ArticleDetailResponseDto(new_article);
    }
    @Transactional
    public List<ArticleListResponseDto> getArticleList() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    public ArticleDetailResponseDto getArticleById(Long id) {
    Article article = articleRepository.findById(id).orElseThrow(
            () -> new ArticleNotFoundException("게시글을 찾을 수 없습니다")
    );
    return new ArticleDetailResponseDto(article);
    }


}
