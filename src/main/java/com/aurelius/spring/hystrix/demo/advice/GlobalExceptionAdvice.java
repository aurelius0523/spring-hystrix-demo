package com.aurelius.spring.hystrix.demo.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public void handle() {
       System.out.println("Exception handler invoked");
    }
}
