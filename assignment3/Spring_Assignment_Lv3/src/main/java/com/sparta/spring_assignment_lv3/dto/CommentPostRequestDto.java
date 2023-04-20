package com.sparta.spring_assignment_lv3.dto;

import lombok.Getter;

@Getter
public class CommentPostRequestDto {
    private final String content;
    private final Long postId;

    public CommentPostRequestDto(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}
