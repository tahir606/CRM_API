package com.example.CRM.Client;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ClientSystem {
    public String filterTime(String filter) {
        String tFilter = "";
        LocalDate now = new LocalDate();
        if (filter.equals("Today")) {
            tFilter = " AND e.timestamp BETWEEN '" + getCurrentDate() + "' AND '" + getCurrentDate() + " 23:59:59'";
        } else if (filter.equals("Last 7 Days")) {
            LocalDate beforeDate = now.minusDays(7);
            now = now.plusDays(1);
            tFilter = " AND e.timestamp BETWEEN '" + beforeDate + "' AND '" + now + "'";
        } else if (filter.equals("Last 30 Days")) {
            LocalDate beforeDate = now.minusDays(30);
            now = now.plusDays(1);
            tFilter = " AND e.timestamp BETWEEN '" + beforeDate + "' AND '" + now + "'";
        } else if (filter.equals("All Time")) {
            tFilter = " ";
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
