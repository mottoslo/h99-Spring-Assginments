package com.sparta.spring_assignment_lv4.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt();
    private final LocalDateTime modifiedAt();
}
