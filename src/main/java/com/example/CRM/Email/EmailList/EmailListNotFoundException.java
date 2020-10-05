package com.example.CRM.Email.EmailList;

public class EmailListNotFoundException extends RuntimeException {
    public EmailListNotFoundException(int id) {
        super("could not found email list " +id);
    }
}
