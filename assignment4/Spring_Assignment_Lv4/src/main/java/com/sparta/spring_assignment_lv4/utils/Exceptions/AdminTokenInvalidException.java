package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class AdminTokenInvalidException extends RuntimeException{

    public AdminTokenInvalidException(String e) {
        super(e);
    }
}
