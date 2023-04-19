package com.sparta.spring_assignment_lv2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRequestDto {
    private String password;

    public DeleteRequestDto(String password) {
        this.password = password;
    }
}
