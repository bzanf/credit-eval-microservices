package io.github.bzanf.creditevaluatorms.infrastructure.exceptions;

import io.github.bzanf.creditevaluatorms.application.exceptions.DataWithInvalidFormatException;
import io.github.bzanf.creditevaluatorms.application.exceptions.MicroserviceCommunicationException;
import io.github.bzanf.creditevaluatorms.domain.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(
            ObjectNotFoundException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MicroserviceCommunicationException.class)
    public ResponseEntity<StandardError> microserviceCommunication(
            MicroserviceCommunicationException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                "Error communicating with microservice",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataWithInvalidFormatException.class)
    public ResponseEntity<StandardError> dataWithInvalidFormat(
            DataWithInvalidFormatException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                "Invalid data",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

}