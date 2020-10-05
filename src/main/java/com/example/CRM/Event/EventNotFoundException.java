package com.example.CRM.Event;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(int id) {
        super("could not found event "+id);
    }
}
