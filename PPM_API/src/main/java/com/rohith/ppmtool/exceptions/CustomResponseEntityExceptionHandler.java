package com.rohith.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectIdException.class)
    public final ResponseEntity<?> handleProjectIdException(ProjectIdException ex, WebRequest request){
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public final ResponseEntity<?> handleProjectNotFound(ProjectNotFoundException exe,WebRequest request){
        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(exe.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectSequenceUpdateException.class)
    public final ResponseEntity<?> handleProjectSequenceUpdate(ProjectSequenceUpdateException exe,WebRequest request){
        return new ResponseEntity<>(new ProjectSequenceUpdateExceptionResponse(exe.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
