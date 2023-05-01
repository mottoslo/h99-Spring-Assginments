package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class UnAuthorizedRequestException extends RuntimeException{
    public UnAuthorizedRequestException(String e) {
        super(e);
    }
}
