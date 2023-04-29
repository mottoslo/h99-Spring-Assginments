package com.sparta.spring_assignment_lv4.controller;


import com.sparta.spring_assignment_lv4.dto.ArticleDetailResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticleListResponseDto;
import com.sparta.spring_assignment_lv4.dto.ArticlePostRequestDto;
import com.sparta.spring_assignment_lv4.service.ArticleService;
import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
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
        System.out.println("articleService.getArticleList().size() = " + articleService.getArticleList().size());
        return articleService.getArticleList();
    }

    @PostMapping
    public ArticleDetailResponseDto postArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ArticlePostRequestDto requestDto
    ){
        return articleService.postArticle(userDetails.getUser(), requestDto);
    }



}
