package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Integer numLikes;
    private final Integer numChildComment;
    private final Boolean thisUserLiked;

    public CommentResponseDto(
            Long id,
            String content,
            String author,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Integer numLikes,
            Integer numChildComment,
            Boolean isDeleted,
            Boolean userLiked
    ){
        this.id = id;
        this.content = isDeleted? "삭제된 댓글입니다" : content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.numLikes = numLikes;
        this.numChildComment = numChildComment;
        this.thisUserLiked = userLiked;
    }
    public CommentResponseDto(Comment comment) {
        this(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUserId(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getNumLikes(),
                comment.getNumChildComment(),
                comment.getIsDeleted(),
                false
        );
    }

    public CommentResponseDto(Comment comment, List<Long> userLikedCommentIds) {
        this(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUserId(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getNumLikes(),
                comment.getNumChildComment(),
                comment.getIsDeleted(),
                userLikedCommentIds.contains(comment.getId())
        );
    }
}
