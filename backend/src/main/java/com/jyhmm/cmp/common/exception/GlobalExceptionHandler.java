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
        return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        log.debug(e.getCause().toString());
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidDTOException.class)
    public ResponseEntity<Object> invalidDTOExceptionHandler(InvalidDTOException e) {
        log.debug(e.getCause().toString());
        return ResponseEntity.unprocessableEntity().body(e.getLocalizedMessage());
    }
}
