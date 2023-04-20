package com.sparta.spring_assignment_lv3.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private final String title;
    private final String content;

    public ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
