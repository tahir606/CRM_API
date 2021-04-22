package com.example.CRM.keyword;

public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException(int id){
        super("Could not found Keyword : "+id);
    }
}
