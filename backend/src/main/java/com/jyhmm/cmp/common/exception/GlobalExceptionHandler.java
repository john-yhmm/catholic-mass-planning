package com.jyhmm.cmp.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception e) {
        log.debug(e.getCause().toString());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ObjectInvalidException.class)
    public ResponseEntity<Object> handleObjectInvalidException(ObjectInvalidException e) {
        return ResponseEntity.unprocessableEntity().body(e.getErrorMessages());
    }
}
