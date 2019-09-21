package com.syncsort.kanban.ppmtool.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
  
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MapValidationErrorService {

    public ResponseEntity<?> getValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (a1, a2) ->a1));
            return new ResponseEntity<Map<String, String>>(validationErrors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
