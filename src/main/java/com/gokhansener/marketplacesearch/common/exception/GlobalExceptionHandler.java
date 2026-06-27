package com.gokhansener.marketplacesearch.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return buildValidationErrorResponse(extractValidationErrors(exception.getBindingResult()));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBindException(BindException exception) {
        return buildValidationErrorResponse(extractValidationErrors(exception));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String errorMessage = "Invalid value for parameter: " + exception.getName();

        return ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request parameter")
                .errors(List.of(errorMessage))
                .timestamp(LocalDateTime.now())
                .build();
    }

    private ApiErrorResponse buildValidationErrorResponse(List<String> errors) {
        return ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private List<String> extractValidationErrors(org.springframework.validation.BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();

        bindingResult.getFieldErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        bindingResult.getGlobalErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return errors;
    }
}