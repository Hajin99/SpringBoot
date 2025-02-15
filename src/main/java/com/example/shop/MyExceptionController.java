package com.example.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyExceptionController {
    //특정 에러상황에서 각 다른 코드를 불러올 수 있음
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<String> handler1(){
        return ResponseEntity.status(404).body("니잘못");
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handler(){
        return ResponseEntity.status(404).body("니잘못");
    }
}
