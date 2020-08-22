package com.example.CRM.Contact;

public class Contact_StoreNotFoundException extends RuntimeException{
    public Contact_StoreNotFoundException(int id) {
        super("Could not found Contact_Store: "+id);
    }
}
