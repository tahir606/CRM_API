package com.example.CRM.Source;

public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException(int id) {
        super("Could Not Found Source List "+id);
    }
}
