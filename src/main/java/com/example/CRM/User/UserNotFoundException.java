package com.example.CRM.User;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(int id){
        super("Could not found User: "+id);
    }
}
