package com.server.socialmediaapi.api;

import com.server.socialmediaapi.exceptions.ValidationException;
import com.server.socialmediaapi.exceptions.CustomErrorResponse;
import com.server.socialmediaapi.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class HandlerExceptionController {

    @ExceptionHandler
    private ResponseEntity<CustomErrorResponse> handleException(EntityNotFoundException ex){
        CustomErrorResponse response = new CustomErrorResponse(List.of(ex.getMessage()), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomErrorResponse> handleException(ValidationException ex){
        CustomErrorResponse response = new CustomErrorResponse(ex.getValidationErrors(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
