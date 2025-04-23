package br.com.fatec.burguerboss.infra.exception;

// ...existing imports...

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DataErrorResponse> handleAccessDeniedException() {
        DataErrorResponse response = new DataErrorResponse(
                "Acesso negado. Você não possui permissão de administrador.",
                HttpStatus.FORBIDDEN,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DataErrorResponse> handleAuthenticationException() {
        DataErrorResponse response = new DataErrorResponse(
                "Falha na autenticação. Verifique suas credenciais.",
                HttpStatus.UNAUTHORIZED,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        DataErrorResponse response = new DataErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DataErrorResponse> handleNullPointerException(NullPointerException ex) {
        DataErrorResponse response = new DataErrorResponse(
                "A null value was encountered where it is not allowed.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataErrorResponse> resolveException(Exception ex) {
        DataErrorResponse response = new DataErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
