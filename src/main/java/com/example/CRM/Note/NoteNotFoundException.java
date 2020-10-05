package com.example.CRM.Note;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(int id) {
        super("Could Not Found Note store "+id);
    }
}
