package com.ss.utopia.orchestrator.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ControllerAdvisor {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

    Map<String, Object> response = new HashMap<>();
    response.put("error", "Invalid field(s) in request.");
    response.put("status", 400);

    var errors = ex.getBindingResult()
        .getAllErrors()
        .stream()
        .collect(
            Collectors.toMap(error -> ((FieldError) error).getField(),
                             error -> getErrorMessageOrDefault((FieldError) error)));

    response.put("message", errors);
    return response;
  }

  private String getErrorMessageOrDefault(FieldError error) {
    var msg = error.getDefaultMessage();
    return msg == null ? "Unknown validation failure" : msg;
  }

  @ExceptionHandler(value = HttpClientErrorException.class)
  private ResponseEntity<?> httpClientErrorException(HttpClientErrorException ex) {
    return ResponseEntity.status(ex.getStatusCode())
        .headers(ex.getResponseHeaders())
        .body(ex.getResponseBodyAsByteArray());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(HttpServerErrorException.class)
  private ResponseEntity<?> httpServerErrorException(HttpServerErrorException ex) {
    return ResponseEntity.status(ex.getStatusCode())
        .build();
  }
}
