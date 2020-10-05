package com.example.CRM.Domain;

public class DomainListNotFoundException extends RuntimeException {
    public DomainListNotFoundException(int id) {
        super("counld not found domain"+ id);
    }
}
