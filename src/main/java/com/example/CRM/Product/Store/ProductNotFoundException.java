package com.example.CRM.Product.Store;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Could not found product store "+id);
    }
}
