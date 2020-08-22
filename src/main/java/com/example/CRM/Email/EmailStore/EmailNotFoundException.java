package com.example.CRM.Email.EmailStore;

public class EmailNotFoundException extends RuntimeException {
    EmailNotFoundException(int id){
        super("Could not found User: "+id);}
}
