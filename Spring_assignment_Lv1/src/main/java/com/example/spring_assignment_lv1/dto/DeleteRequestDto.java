package com.example.spring_assignment_lv1.dto;

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
