package com.example.CRM.Task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super("Could Not found task "+id);
    }
}
