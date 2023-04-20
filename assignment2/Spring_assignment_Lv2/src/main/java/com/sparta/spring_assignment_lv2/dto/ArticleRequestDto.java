package com.sparta.spring_assignment_lv2.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String title;
    private String author;
    private String content;

    public ArticleRequestDto(String title, String author, String password, String content) {
        this.title = title;
        this.content = content;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
