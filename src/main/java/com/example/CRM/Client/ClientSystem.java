package com.example.CRM.Client;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ClientSystem {
    public String filterTime(String from, String to) {
        String tFilter = "";
        if (!from.equals("") && !to.equals("")) {
            tFilter = " AND e.timestamp BETWEEN '" + from + "' AND '" + to + "'";
        }
        return tFilter;
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
