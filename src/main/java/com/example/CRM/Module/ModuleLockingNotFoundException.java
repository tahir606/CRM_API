package com.example.CRM.Module;

public class ModuleLockingNotFoundException extends RuntimeException{
    public ModuleLockingNotFoundException(int id) {
        super("could not found module locking " +id);
    }
}
