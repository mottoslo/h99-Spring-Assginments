package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListResponseDto {
    private String title;
    private String author;

    public ArticleListResponseDto(Article article) {
        this.title = article.getTitle();
        this.author = article.getUser().getUserId();
    }
}
