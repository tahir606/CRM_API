package com.example.CRM.Email.EmailSent;

public class EmailSentNotFoundException extends RuntimeException {
    public EmailSentNotFoundException(int id) {
        super("Could not found Email Sent: "+id);
    }
}
