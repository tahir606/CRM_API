package com.example.CRM.Client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class Client_StoreNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(Client_StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserNotFoundHandler(Client_StoreNotFoundException ex){
        return ex.getMessage();
    }
}
