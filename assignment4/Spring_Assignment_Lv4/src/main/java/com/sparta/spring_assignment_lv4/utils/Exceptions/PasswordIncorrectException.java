package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String e) {
       super(e);
    }
}
