package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Comment;
import com.sparta.spring_assignment_lv4.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentPostRequestDto {
    private String content;

    public Comment toComment(User user, Long rootArticleId){
        return new Comment(
                this.content,
                user,
                rootArticleId
        );
    }

    public Comment toComment(User user, Long rootArticleId, Long rootCommentId) {
        return new Comment(
                this.content,
                user,
                rootArticleId,
                rootCommentId
        );
    }
}
