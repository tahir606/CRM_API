package com.example.CRM.Email.History;

public class HistoryNotFoundException extends RuntimeException {
    public HistoryNotFoundException(int id) {
        super("Could not found history: " + id);
    }
}
