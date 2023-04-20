package com.sparta.spring_assignment_lv2.dto;


import com.sparta.spring_assignment_lv2.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long id;
    private final String title;
    private final String author;
    private final String content;

    public ArticleResponseDto(Article article) {   //한번에 안되나 ?
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getUser().getUserId();
        this.content = article.getContent();
    }

}
