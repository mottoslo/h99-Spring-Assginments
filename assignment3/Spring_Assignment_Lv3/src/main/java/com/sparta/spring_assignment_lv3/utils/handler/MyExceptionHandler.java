package com.sparta.spring_assignment_lv3.utils.handler;


import com.sparta.spring_assignment_lv3.utils.Exceptions.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalExceptionHandler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullExceptionHandler(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> AccessExceptionHandler(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

        @ExceptionHandler(MyException.class)
        public ResponseEntity<Map<String, String>> exceptionHandler(MyException e){

        Map<String,String> exceptionInfo = new HashMap<>();

        exceptionInfo.put("error code", String.valueOf(e.getHttpStatusCode()));
        exceptionInfo.put("error desc", e.getHttpStatusReason());
        exceptionInfo.put("message", e.getMessage());

        return new ResponseEntity<>(exceptionInfo, e.getHttpStatus());
    }



}
