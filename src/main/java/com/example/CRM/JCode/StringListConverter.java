package com.example.CRM.JCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Collections.*;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return String.join(SPLIT_CHAR,stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (string == null) {
            return new ArrayList<>();
        } else if (string.equals("")) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(string.split(SPLIT_CHAR));
        }

    }
}