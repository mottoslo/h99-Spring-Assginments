package com.sparta.spring_assignment_lv3.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private Boolean success;

    public DeleteResponseDto(Boolean success) {
        this.success = success;
    }
}
