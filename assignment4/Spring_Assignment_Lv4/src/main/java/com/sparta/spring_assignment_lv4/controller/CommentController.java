package com.sparta.spring_assignment_lv4.controller;

import com.sparta.spring_assignment_lv4.entity.Article;
import com.sparta.spring_assignment_lv4.service.CommentService;
import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}")
    public Article commentOnArticle(@PathVariable Long articleId){
        return commentService.commentOnArticle(articleId);

    }

    @PostMapping("/{articleId}/{commentId}")
    public Article commentOnComment(@PathVariable Long articleId, @PathVariable Long commentId){
        return commentService.commentOnComment(articleId, commentId);

    }
}
