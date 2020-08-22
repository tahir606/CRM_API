package com.example.CRM.Client;

public class Client_StoreNotFoundException extends RuntimeException {
    Client_StoreNotFoundException(int id){
        super("Could not found ClientStore: "+id);
    }
}
