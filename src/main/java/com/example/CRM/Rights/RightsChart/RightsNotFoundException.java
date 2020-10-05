package com.example.CRM.Rights.RightsChart;

public class RightsNotFoundException extends RuntimeException {
    public RightsNotFoundException(int id) {
        super("Could Not found Rights Chart "+id);
    }
}
