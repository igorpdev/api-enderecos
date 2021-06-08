package org.igorpdev.apienderecos.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.igorpdev.apienderecos.error.ErrorDetails;
import org.igorpdev.apienderecos.error.ResourceNotFoundDetails;
import org.igorpdev.apienderecos.error.ResourceNotFoundException;
import org.igorpdev.apienderecos.error.UserExistsDetails;
import org.igorpdev.apienderecos.error.UserExistsException;
import org.igorpdev.apienderecos.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails eDetails = ErrorDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(status.value())
            .title("Internal Exception")
            .detail(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .build();
        return new ResponseEntity<> (eDetails, headers, status);
	}

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundExeception(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.NOT_FOUND.value())
            .title("Resource not found")
            .detail(rnfException.getMessage())
            .developerMessage(rnfException.getClass().getName())
            .build();
        return new ResponseEntity<> (rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<?> handlerUserExistsException(UserExistsException userExistsException) {
        UserExistsDetails ueDetails = UserExistsDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Usuário existente")
            .detail(userExistsException.getMessage())
            .developerMessage(userExistsException.getClass().getName())
            .build();
        return new ResponseEntity<> (ueDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Campos inválidos")
            .detail(ex.getMessage())
            .developerMessage(ex.getClass().getName())
            .field(fields)
            .fieldMessage(fieldMessages)
            .build();
        return new ResponseEntity<> (veDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handlerInvalidFormatException(InvalidFormatException invalidFormatException) {
        ErrorDetails eDetails = ErrorDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Campos inválidos")
            .detail(invalidFormatException.getMessage())
            .developerMessage(invalidFormatException.getClass().getName())
            .build();
        return new ResponseEntity<> (eDetails, HttpStatus.BAD_REQUEST);
    } 

}
