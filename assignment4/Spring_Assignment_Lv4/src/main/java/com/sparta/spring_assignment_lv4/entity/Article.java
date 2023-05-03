package com.sparta.spring_assignment_lv4.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private User user;

    @Column
    private Boolean isDeleted = false;

    @Column
    private Integer numLikes = 0;

    @CreatedDate
    private LocalDateTime CreatedAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Article(String content, String title, User user) {
        this.content = content;
        this.title = title;
        this.user = user;
    }

    public void updateContent(String content){
        this.content = content;
    }
    public void flagDeleted(){ this.isDeleted = true; }

    public void addLike() { this.numLikes++; }
    public void cancelLike() { this.numLikes--; }
}
