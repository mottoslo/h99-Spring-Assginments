package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;

    private final String author;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getIsDeleted()? "삭제된 댓글입니다" : comment.getContent();
        this.author = comment.getUser().getUserId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

    }
}
