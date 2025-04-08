package br.com.fatec.burguerboss.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> erroGlobal(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
