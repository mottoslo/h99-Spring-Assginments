package com.sparta.spring_assignment_lv4.utils.Exceptions;

public class UserIdExistsException extends RuntimeException{
    public UserIdExistsException(String e){
        super(e);
    }
}
