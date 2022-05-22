package com.zemoso.greencommute.controller;

import com.zemoso.greencommute.dto.ResponseDTO;
import com.zemoso.greencommute.exception.DataNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException de) {
        return new ResponseEntity<>(new ResponseDTO("failure", "data not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> otherExceptions(Exception e) {
        return new ResponseEntity<>(new ResponseDTO("failure", "internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
