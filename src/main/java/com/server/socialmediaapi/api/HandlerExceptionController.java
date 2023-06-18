package com.server.socialmediaapi.api;

import com.server.socialmediaapi.exceptions.BadRequestException;
import com.server.socialmediaapi.exceptions.CustomErrorResponse;
import com.server.socialmediaapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class HandlerExceptionController {

    @ExceptionHandler
    private ResponseEntity<CustomErrorResponse> handleException(NotFoundException ex){
        CustomErrorResponse response = new CustomErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomErrorResponse> handleException(BadRequestException ex){
        CustomErrorResponse response = new CustomErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
