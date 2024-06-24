/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.exceptions;

import java.security.InvalidAlgorithmParameterException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author btocn
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(SupplierNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetail> handleSupplierNotFoundException(SupplierNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetails = new ErrorDetail(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(true));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
    
    @ExceptionHandler(InvalidSupplierException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorDetail> handleInvalidSupplierException(InvalidSupplierException ex, WebRequest request){
        ErrorDetail errorDetails = new ErrorDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), request.getDescription(true));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDetails);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDetail> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.CONFLICT, "Duplicate key value violates unique constraint", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetail);
    }
    
}
