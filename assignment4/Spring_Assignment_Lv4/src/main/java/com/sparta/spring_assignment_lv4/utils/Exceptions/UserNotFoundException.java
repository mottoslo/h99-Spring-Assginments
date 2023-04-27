package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String e) {
        super(e);
    }
}
