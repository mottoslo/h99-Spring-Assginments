package com.example.spring_assignment_lv1.service;

import com.example.spring_assignment_lv1.dto.ArticleRequestDto;
import com.example.spring_assignment_lv1.dto.ArticleResponseDto;
import com.example.spring_assignment_lv1.dto.DeleteRequestDto;
import com.example.spring_assignment_lv1.dto.DeleteResponseDto;
import com.example.spring_assignment_lv1.entity.Article;
import com.example.spring_assignment_lv1.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    private Article findById(Long id){
        return articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }

    private Boolean checkpw(Article article, String password){
        return article.getPassword().equals(password);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getAllArticle() {
        return articleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    } // 내림차순으로 정렬하여 ResponseDto로 반환

    @Transactional
    public ArticleResponseDto postArticle(ArticleRequestDto requestDto) {
        Article new_article = new Article(requestDto);
        articleRepository.save(new_article);
        return new ArticleResponseDto(new_article);
    }

    @Transactional
    public ArticleResponseDto getArticleById(Long id) {
        Article found = findById(id);
        return new ArticleResponseDto(found);
    }
    @Transactional
    public ArticleResponseDto editArticleById(Long id, ArticleRequestDto requestDto) {
        Article found = findById(id);
        if (checkpw(found, requestDto.getPassword())) {
            found.update(requestDto);
        }
        return new ArticleResponseDto(found);
    }
    @Transactional
    public DeleteResponseDto deleteArticleById(Long id, DeleteRequestDto requestDto) {
        Article found = findById(id);
        if(checkpw(found, requestDto.getPassword())) {
            articleRepository.deleteById(id);
            return new DeleteResponseDto(true);
        }
        return new DeleteResponseDto(false);

    }
}
