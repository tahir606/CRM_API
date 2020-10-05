package com.example.CRM.Phone;

public class phoneListNotFoundException extends RuntimeException{
    public phoneListNotFoundException(int id) {
        super("Could not found phone list "+id);
    }
}
