package com.sparta.spring_assignment_lv3.utils.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends Exception{
    private final HttpStatus httpStatus;

    public MyException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatusCode(){
        return httpStatus.value();
    }

    public String getHttpStatusReason(){
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;

    }
}
