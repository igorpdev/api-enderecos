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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultErrorException(Exception exception) {
        ErrorDetails eDetails = ErrorDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Erro")
            .detail(exception.getMessage())
            .developerMessage(exception.getClass().getName())
            .build();
        return new ResponseEntity<> (eDetails, HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException maNotValidException) {
        
        List<FieldError> fieldErrors = maNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.BAD_REQUEST.value())
            .title("Campos inválidos")
            .detail(maNotValidException.getMessage())
            .developerMessage(maNotValidException.getClass().getName())
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
