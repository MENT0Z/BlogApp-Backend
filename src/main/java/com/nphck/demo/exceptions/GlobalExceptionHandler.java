package com.nphck.demo.exceptions;

import com.nphck.demo.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>ResponseNotFoundExceptionHandler(ResourceNotFoundException exceptionn )
    {
        String message = exceptionn.getMessage();
        ApiResponse responsee = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(responsee, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>ArgsNotValidExceptinHandler(MethodArgumentNotValidException exp){
        Map<String,String>responsee = new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName = ((FieldError)objectError).getField();
            String messagee = objectError.getDefaultMessage();
            responsee.put(fieldName,messagee);
        }));
        return new ResponseEntity<Map<String,String>>(responsee,HttpStatus.BAD_REQUEST);
    }

}

