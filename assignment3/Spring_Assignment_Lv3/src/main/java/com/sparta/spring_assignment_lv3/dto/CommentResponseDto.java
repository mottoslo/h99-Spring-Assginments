package com.sparta.spring_assignment_lv3.dto;

import com.sparta.spring_assignment_lv3.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long Id;
    private final String content;
    private final String userId;

    public CommentResponseDto(Comment comment) {
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.Id = comment.getId();
        this.content = comment.getContent();
        this.userId = comment.getUser().getUserId();
    }
}
