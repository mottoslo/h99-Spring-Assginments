package com.sparta.spring_assignment_lv4.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article rootArticle;

    @Column
    private Long rootCommentId;

    @CreatedDate
    private LocalDateTime CreatedAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
