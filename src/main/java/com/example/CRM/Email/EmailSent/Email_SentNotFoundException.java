package com.example.CRM.Email.EmailSent;

public class Email_SentNotFoundException extends RuntimeException {
    public Email_SentNotFoundException(int id) {
        super("Could not found Email Sent: "+id);
    }
}
