package br.com.fatec.burguerboss.infra.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record DataErrorResponse(String message, HttpStatus httpStatus, LocalDateTime time) {
}
