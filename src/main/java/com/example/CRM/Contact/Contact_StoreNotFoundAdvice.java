package com.example.CRM.Contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Contact_StoreNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(Contact_StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserNotFoundHandler(Contact_StoreNotFoundException ex){
        return ex.getMessage();
    }
}
