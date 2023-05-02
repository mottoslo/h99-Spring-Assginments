package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListResponseDto {
    private final String title;
    private final String author;
    private final Integer numLikes;

    public ArticleListResponseDto(Article article) {
        this.title = article.getTitle();
        this.author = article.getUser().getUserId();
        this.numLikes = article.getNumLikes();
    }
}
