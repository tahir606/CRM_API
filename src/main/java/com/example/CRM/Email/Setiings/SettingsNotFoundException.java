package com.example.CRM.Email.Setiings;

public class SettingsNotFoundException extends RuntimeException {
    public SettingsNotFoundException(int id) {
        super("Setting not Found: "+id);
    }
}
