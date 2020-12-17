package com.bilgeadam.tutorial.springboot.controllers;

import com.bilgeadam.tutorial.springboot.utils.IllegalRestArgument;
import com.bilgeadam.tutorial.springboot.utils.RecordNotFound;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalRestArgument.class)
    public ResponseEntity<Object> handleIllegalArgument(@NotNull IllegalRestArgument ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<Object> handleRecordNotFound(@NotNull RecordNotFound ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
