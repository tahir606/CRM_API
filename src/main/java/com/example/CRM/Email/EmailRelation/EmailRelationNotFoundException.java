package com.example.CRM.Email.EmailRelation;

public class EmailRelationNotFoundException extends RuntimeException {
    public EmailRelationNotFoundException(int id) {
        super("could not found email Relation " +id);
    }
}
