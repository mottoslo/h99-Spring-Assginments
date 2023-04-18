package com.example.spring_assignment_lv1.controller;

import com.example.spring_assignment_lv1.dto.ArticleRequestDto;
import com.example.spring_assignment_lv1.dto.ArticleResponseDto;
import com.example.spring_assignment_lv1.dto.DeleteRequestDto;
import com.example.spring_assignment_lv1.dto.DeleteResponseDto;
import com.example.spring_assignment_lv1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/article")
    public List<ArticleResponseDto> getAllArticle(){
        return articleService.getAllArticle();
    }  //모든 게시물 가져오기

    @PostMapping("/article")
    public ArticleResponseDto postArticle(@RequestBody ArticleRequestDto requestDto){
        return articleService.postArticle(requestDto);
    } // 신규 게시물 등록

    @GetMapping("/article/{id}")
    public ArticleResponseDto getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    } // 게시물 ID로 찾기

    @PutMapping("/article/{id}")
    public ArticleResponseDto editArticleById(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){
        return articleService.editArticleById(id, requestDto);
    }

    @DeleteMapping("/article/{id}")
    public DeleteResponseDto deleteArticleById(@PathVariable Long id, @RequestBody DeleteRequestDto requestDto){
        return articleService.deleteArticleById(id, requestDto);
    }




}
