package com.example.CRM.Email.EmailSent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmailSentNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EmailSentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UserNotFoundHandler(EmailSentNotFoundException ex){
        return ex.getMessage();
    }
}
