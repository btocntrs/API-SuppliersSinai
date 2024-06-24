/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btocntrs.ProveedoresSinaiAPI.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author btocn
 */
@Data
public class ErrorDetail {
    
    private HttpStatus status;
    private String message;
    private String detail;

    public ErrorDetail(HttpStatus status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
    
    
}
