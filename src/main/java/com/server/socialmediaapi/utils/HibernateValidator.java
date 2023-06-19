package com.server.socialmediaapi.utils;

import com.server.socialmediaapi.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Component
public class HibernateValidator {
    public void validate(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(createValidationErrors((bindingResult)));
        }
    }

    public static List<String> createValidationErrors(BindingResult bindingResult){
        List<String> validationErrors = new ArrayList<>();
        List<FieldError> errors = bindingResult.getFieldErrors();

        for(FieldError error : errors)
            validationErrors.add(error.getField() + " - " + error.getDefaultMessage());

        return validationErrors;
    }
}
