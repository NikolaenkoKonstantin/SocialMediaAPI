package com.server.socialmediaapi.utils;

import com.server.socialmediaapi.exceptions.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class HibernateValidator {
    public void validate(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = ErrorMessage.createErrorMsg(bindingResult);
            throw new BadRequestException(errorMsg.toString());
        }
    }
}
