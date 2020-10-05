package com.example.CRM.NotificationSettings;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(int id) {
        super("could Not Found Notification Settings "+id);
    }
}
