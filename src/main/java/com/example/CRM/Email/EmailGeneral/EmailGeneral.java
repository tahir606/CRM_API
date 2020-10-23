package com.example.CRM.Email.EmailGeneral;

import com.example.CRM.Email.Email;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "email_general")
public class EmailGeneral extends Email {

    public EmailGeneral(){

    }

    public EmailGeneral(int code, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                        String body, String attachment, Timestamp timestamp, int freeze) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress, bccAddress, body, attachment, timestamp, freeze);
    }

}
