package com.example.CRM.User;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import org.joda.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Service
public class UserSystem {
    private final UserRepository userRepository;

    public UserSystem(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUserStatus(Users users) {
        if (users.getUserCode() != 0) {
            userRepository.save(users);
        }
    }

    public String filterTime(String from,String to) {
        String tFilter = "";
        if (!from.equals("") && !to.equals("")) {
            tFilter = " AND h.time BETWEEN '" + from + "' AND '" + to + "'";
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
