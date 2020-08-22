package com.example.CRM.Email.EmailSent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Email_SentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(Email_SentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserNotFoundHandler(Email_SentNotFoundException ex){
        return ex.getMessage();
    }
}
