package com.sparta.spring_assignment_lv4.entity;

import com.sparta.spring_assignment_lv4.dto.ArticlePostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String content;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @CreatedDate
    private LocalDateTime CreatedAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Article(String content, String title, User user) {
        this.content = content;
        this.title = title;
        this.user = user;
    }
}
