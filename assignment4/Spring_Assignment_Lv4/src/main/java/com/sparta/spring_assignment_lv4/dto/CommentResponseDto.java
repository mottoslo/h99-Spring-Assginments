package com.sparta.spring_assignment_lv4.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sparta.spring_assignment_lv4.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;

    private final String author;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Integer numLikes;
    private final Boolean thisUserLiked;

    @QueryProjection
    public CommentResponseDto(
            Long id,
            String content,
            String author,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Integer numLikes,
            Boolean isDeleted,
            Boolean thisUserLiked
    ){
        this.id = id;
        this.content = isDeleted? "삭제된 댓글입니다" : content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.numLikes = numLikes;
        this.thisUserLiked = thisUserLiked;
    }


    public CommentResponseDto(Comment comment) {
        this(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUserId(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getNumLikes(),
                comment.getIsDeleted(),
                false
        );
    }
}
