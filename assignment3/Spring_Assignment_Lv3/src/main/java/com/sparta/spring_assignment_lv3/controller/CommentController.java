package com.sparta.spring_assignment_lv3.controller;

import com.sparta.spring_assignment_lv3.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentPostRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv3.dto.DeleteResponseDto;
import com.sparta.spring_assignment_lv3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto postComment(
            @RequestBody CommentPostRequestDto requestDto,
            HttpServletRequest requestToken
    ){
        return commentService.postComment(requestDto, requestToken);
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto editComment(
            @PathVariable Long id,
            @RequestBody CommentEditRequestDto requestDto,
            HttpServletRequest requestToken
    ){
        return commentService.editComment(id, requestDto, requestToken);
    }

    @DeleteMapping("/comment/{id}")
    public DeleteResponseDto deleteComment(
            @PathVariable Long id,
            HttpServletRequest requestToken
    ){
        return commentService.deleteComment(id, requestToken);
    }
}
