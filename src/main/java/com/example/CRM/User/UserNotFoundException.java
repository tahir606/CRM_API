package com.example.CRM.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id){
        super("Could not found User: "+id);
    }
}
