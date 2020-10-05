package com.example.CRM.LeadStore;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(int id) {
        super("could not found leads store "+ id);
    }
}
