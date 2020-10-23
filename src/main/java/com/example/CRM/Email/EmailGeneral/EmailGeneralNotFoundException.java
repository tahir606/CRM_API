package com.example.CRM.Email.EmailGeneral;

public class EmailGeneralNotFoundException extends RuntimeException {

    public EmailGeneralNotFoundException(int id) {
        super("Could not found Email General: " + id);
    }
}
