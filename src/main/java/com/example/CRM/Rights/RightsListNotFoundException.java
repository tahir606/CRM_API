package com.example.CRM.Rights;

public class RightsListNotFoundException extends RuntimeException{
    public RightsListNotFoundException(int id) {
        super("Could not found Rights list "+id);
    }
}
