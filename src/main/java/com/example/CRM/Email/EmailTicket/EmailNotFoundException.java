package com.example.CRM.Email.EmailTicket;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(int id){
        super("Could not found User: " + id);}
}
