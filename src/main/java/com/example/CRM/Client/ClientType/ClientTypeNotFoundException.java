package com.example.CRM.Client.ClientType;

public class ClientTypeNotFoundException extends RuntimeException {
    public ClientTypeNotFoundException(int id) {
        super("Could not found ClientType: "+id);
    }
}
