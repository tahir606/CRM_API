package com.example.CRM.Contact;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(int id) {
        super("Could not found Contact_Store: "+id);
    }
}
