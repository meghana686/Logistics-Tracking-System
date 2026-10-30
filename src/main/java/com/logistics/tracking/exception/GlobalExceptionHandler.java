package com.logistics.tracking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.logistics.tracking.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleINFE(
            IdNotFoundException exception) {

        ResponseStructure<String> res = new ResponseStructure<>();

        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(exception.getMessage());
        res.setData("Failure");

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoRecordAvailableException.class)
    public ResponseEntity<ResponseStructure<String>> handleNRAE(
            NoRecordAvailableException exception) {

        ResponseStructure<String> res = new ResponseStructure<>();

        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(exception.getMessage());
        res.setData("Failure");

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseStructure<String>> handleIllegalArgumentException(
            IllegalArgumentException exception) {

        ResponseStructure<String> res = new ResponseStructure<>();

        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setMessage(exception.getMessage());
        res.setData("Failure");

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseStructure<String>> handleIllegalStateException(
            IllegalStateException ex) {

        ResponseStructure<String> res = new ResponseStructure<>();
        res.setData(null);
        res.setMessage(ex.getMessage());
        res.setStatusCode(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }
}