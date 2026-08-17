package com.example.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        log.warn(
                "Business exception: traceId={}, code={}, message={}",
                traceId,
                ex.getCode(),
                ex.getMessage()
        );

        return buildResponse(
                ex.getStatus(),
                HttpStatus.valueOf(ex.getStatus()).getReasonPhrase(),
                ex.getCode(),
                ex.getMessage(),
                request.getRequestURI(),
                traceId,
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        log.warn(
                "Validation failed: traceId={}, errors={}",
                traceId,
                errors
        );

        return buildResponse(
                400,
                "Bad Request",
                "VALIDATION_ERROR",
                "Request validation failed",
                request.getRequestURI(),
                traceId,
                errors
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getConstraintViolations()
                .forEach(error ->
                        errors.put(
                                error.getPropertyPath().toString(),
                                error.getMessage()
                        )
                );

        return buildResponse(
                400,
                "Bad Request",
                "VALIDATION_ERROR",
                "Request validation failed",
                request.getRequestURI(),
                traceId,
                errors
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        log.warn(
                "Malformed JSON request: traceId={}, path={}",
                traceId,
                request.getRequestURI()
        );

        return buildResponse(
                400,
                "Bad Request",
                "MALFORMED_REQUEST",
                "Request body is invalid or malformed",
                request.getRequestURI(),
                traceId,
                null
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        return buildResponse(
                400,
                "Bad Request",
                "INVALID_PARAMETER",
                "Invalid value for parameter: " + ex.getName(),
                request.getRequestURI(),
                traceId,
                null
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        log.error(
                "Database constraint violation: traceId={}",
                traceId,
                ex
        );

        return buildResponse(
                409,
                "Conflict",
                "DATA_CONFLICT",
                "Request conflicts with existing data",
                request.getRequestURI(),
                traceId,
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request
    ) {

        String traceId = getTraceId(request);

        log.error(
                "Unexpected exception: traceId={}, method={}, path={}",
                traceId,
                request.getMethod(),
                request.getRequestURI(),
                ex
        );

        return buildResponse(
                500,
                "Internal Server Error",
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Please contact support with the trace ID.",
                request.getRequestURI(),
                traceId,
                null
        );
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            int status,
            String error,
            String code,
            String message,
            String path,
            String traceId,
            Map<String, String> fieldErrors
    ) {

        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                status,
                error,
                code,
                message,
                path,
                traceId,
                fieldErrors
        );

        return ResponseEntity
                .status(status)
                .body(response);
    }

    private String getTraceId(HttpServletRequest request) {

        String traceId = request.getHeader("X-Trace-Id");

        return traceId == null || traceId.isBlank()
                ? UUID.randomUUID().toString()
                : traceId;
    }
}
