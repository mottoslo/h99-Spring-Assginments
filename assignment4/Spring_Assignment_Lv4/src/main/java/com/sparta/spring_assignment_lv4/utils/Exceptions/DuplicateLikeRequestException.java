package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class DuplicateLikeRequestException extends RuntimeException{
    public DuplicateLikeRequestException(String e) {
        super(e);
    }
}
