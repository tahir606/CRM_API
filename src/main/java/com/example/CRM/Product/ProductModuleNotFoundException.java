package com.example.CRM.Product;

public class ProductModuleNotFoundException extends RuntimeException {
    public ProductModuleNotFoundException(int id) {
        super("could not Found product module "+id);
    }
}
