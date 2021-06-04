package org.igorpdev.apienderecos.handler;

import java.util.Date;

import org.igorpdev.apienderecos.error.ResourceNotFoundDetails;
import org.igorpdev.apienderecos.error.ResourceNotFoundException;
import org.igorpdev.apienderecos.error.UserExistsDetails;
import org.igorpdev.apienderecos.error.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundExeception(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder
            .newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.NOT_FOUND.value())
            .title("Resource not found")
            .detail(rnfException.getMessage())
            .developerMessage("Usuário não encontrado.")
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
            .developerMessage("Email e/ou CPF já foram cadastrados.")
            .build();
        return new ResponseEntity<> (ueDetails, HttpStatus.BAD_REQUEST);
    }
}
