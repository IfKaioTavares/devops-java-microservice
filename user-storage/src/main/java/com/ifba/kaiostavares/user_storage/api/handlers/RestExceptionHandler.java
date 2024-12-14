package com.ifba.kaiostavares.user_storage.api.handlers;

import com.ifba.kaiostavares.user_storage.api.exceptions.ContentNotFound;
import com.ifba.kaiostavares.user_storage.api.exceptions.EmailAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ContentNotFound.class)
    private ResponseEntity<RestErrorMessage> handleContentNotFound(ContentNotFound ex){
        var threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    private ResponseEntity<RestErrorMessage> handleContentNotFound(EmailAlreadyInUseException ex){
        var threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

}
