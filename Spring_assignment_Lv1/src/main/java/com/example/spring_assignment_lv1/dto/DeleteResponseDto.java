package com.example.spring_assignment_lv1.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private Boolean success;

    public DeleteResponseDto(Boolean success) {
        this.success = success;
    }
}
