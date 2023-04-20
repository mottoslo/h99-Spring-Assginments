package com.sparta.spring_assignment_lv3.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentEditRequestDto {
    private String content;

    public CommentEditRequestDto(String content) {
        this.content = content;
    }
}
