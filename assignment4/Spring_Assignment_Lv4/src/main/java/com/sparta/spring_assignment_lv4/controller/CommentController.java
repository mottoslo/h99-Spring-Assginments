package com.sparta.spring_assignment_lv4.controller;

import com.sparta.spring_assignment_lv4.dto.*;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.service.CommentService;
import com.sparta.spring_assignment_lv4.utils.springSecurity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    //최초댓글달기
    @PostMapping("/{articleId}")
    public CommentResponseDto commentOnArticle(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentPostRequestDto requestDto,
            @PathVariable Long articleId
    ){
        return commentService.commentOnArticle(userDetails.getUser(), requestDto, articleId);
    }

    //대댓글달기
    @PostMapping("/{rootCommentId}/{articleId}")
    public CommentResponseDto commentOnComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentPostRequestDto requestDto,
            @PathVariable Long articleId,
            @PathVariable Long rootCommentId
    ){
        return commentService.commentOnComment(userDetails.getUser(), requestDto, articleId, rootCommentId);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto editComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentEditRequestDto requestDto,
            @PathVariable Long commentId
    ){
        User user = userDetails.getUser();
        return commentService.editComment(user, commentId, requestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId
    ){
        User user = userDetails.getUser();
        commentService.deleteComment(user, commentId);
    }

    @PostMapping("/like/{commentId}")
    public void likeComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId
    ){
        Long userId = userDetails.getUser().getId();
        commentService.likeComment(userId, commentId);
    }

    @DeleteMapping("/like/{commentId}")
    public void likeCancelComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long commentId
    ){
        Long userId = userDetails.getUser().getId();
        commentService.likeCancelComment(userId, commentId);
    }



}
