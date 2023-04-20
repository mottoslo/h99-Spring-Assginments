package com.sparta.spring_assignment_lv3.controller;

import com.sparta.spring_assignment_lv3.dto.ArticleRequestDto;
import com.sparta.spring_assignment_lv3.dto.ArticleResponseDto;
import com.sparta.spring_assignment_lv3.dto.DeleteResponseDto;
import com.sparta.spring_assignment_lv3.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ArticleResponseDto postArticle(@RequestBody ArticleRequestDto requestDto, HttpServletRequest requestToken){
        return articleService.postArticle(requestDto, requestToken);
    } // 신규 게시물 등록

    @GetMapping("/article/{id}")
    public ArticleResponseDto getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    } // 게시물 ID로 찾기

    @PutMapping("/article/{id}")
    public ArticleResponseDto editArticleById(
            @PathVariable Long id,
            @RequestBody ArticleRequestDto requestDto,
            HttpServletRequest requestToken){
        return articleService.editArticleById(id, requestDto, requestToken);
    }
//
    @DeleteMapping("/article/{id}")
    public DeleteResponseDto deleteArticleById(
            @PathVariable Long id,
            HttpServletRequest requestToken
    ){
        return articleService.deleteArticleById(id, requestToken);
    }
}
