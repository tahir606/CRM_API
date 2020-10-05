package com.example.CRM.Client;

public class ClientNotFoundException extends RuntimeException {
    ClientNotFoundException(int id){
        super("Could not found ClientStore: "+id);
    }
}
