package com.sparta.spring_assignment_lv3.entity;


import com.sparta.spring_assignment_lv3.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentPostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;



    public Comment(CommentPostRequestDto requestDto, Users user, Article article) {
        this.content = requestDto.getContent();
        this.user = user;
        this.article = article;
        user.addComment(this);
        article.addComment(this);
    }

    public void update(CommentEditRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
