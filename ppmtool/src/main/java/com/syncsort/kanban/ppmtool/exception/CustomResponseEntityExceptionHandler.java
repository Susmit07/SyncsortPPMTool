package com.syncsort.kanban.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Global exception handling for controllers, all controllers throwing exception specified in this class will come for advice.
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIDException (ProjectIDException ex, WebRequest webRequest) {
        ProjectIDExceptionResponse projectIDException =  new ProjectIDExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(projectIDException, HttpStatus.BAD_REQUEST);
    }
}
