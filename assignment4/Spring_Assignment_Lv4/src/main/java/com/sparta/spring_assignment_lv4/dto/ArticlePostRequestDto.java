package com.sparta.spring_assignment_lv4.dto;

import com.sparta.spring_assignment_lv4.entity.Article;
import com.sparta.spring_assignment_lv4.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticlePostRequestDto {
    private String title;
    private String content;

    public Article toArticle(User user) {
        return new Article(this.content,this.title, user);
    }
}
