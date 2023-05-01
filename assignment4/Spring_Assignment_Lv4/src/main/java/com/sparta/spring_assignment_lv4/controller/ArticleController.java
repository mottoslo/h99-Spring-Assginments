package com.sparta.spring_assignment_lv4.controller;


import com.sparta.spring_assignment_lv4.dto.ArticleDetailResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticleEditRequestDto;
import com.sparta.spring_assignment_lv4.dto.ArticleListResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticlePostRequestDto;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.service.ArticleService;
import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleListResponseDto> getArticleList(){
        return articleService.getArticleList();
    }

    @PostMapping
    public ArticleDetailResponseDto postArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ArticlePostRequestDto requestDto
    ){
        return articleService.postArticle(userDetails.getUser(), requestDto);
    }

    @GetMapping("/{articleId}")
    public ArticleDetailResponseDto getArticleById(@PathVariable Long articleId){
        return articleService.getArticleById(articleId);
    }

    @PutMapping("/{articleId}")
    public ArticleDetailResponseDto editArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long articleId,
            @RequestBody ArticleEditRequestDto requestDto
    ){
        User user = userDetails.getUser();
        return articleService.editArticle(user, articleId, requestDto);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long articleId
    ){
        User user = userDetails.getUser();
        articleService.deleteArticle(user, articleId);
    }



}
