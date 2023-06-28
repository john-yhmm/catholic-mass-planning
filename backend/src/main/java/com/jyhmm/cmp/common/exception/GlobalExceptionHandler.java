package com.jyhmm.cmp.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        log.info(e.getCause().toString());
        return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundHandler(NotFoundException e) {
        log.info(e.getCause().toString());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<Object> invalidEntityHandler(InvalidEntityException e) {
        log.info(e.getCause().toString());
        return ResponseEntity.unprocessableEntity().body(e.getLocalizedMessage());
    }
}
