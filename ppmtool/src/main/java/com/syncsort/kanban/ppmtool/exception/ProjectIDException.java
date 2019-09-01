package com.syncsort.kanban.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Whenever this exception is encountered return a BAD REQUEST to the user
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIDException extends RuntimeException{

    public ProjectIDException(String message) {
        super(message);
    }
}
