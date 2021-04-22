package com.example.CRM.Email.EmailGeneral;

import com.example.CRM.Email.Email;

import javax.mail.Address;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "email_general")
public class EmailGeneral extends Email {

    public EmailGeneral(){

    }

    public EmailGeneral(int code, int messageNo, String subject, List<String> toAddress, List<String>  fromAddress, List<String>  ccAddress, List<String>  bccAddress,
                        String body, List<String> attachment, Timestamp timestamp, int freeze) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress,  bccAddress, body, attachment, timestamp, freeze);

    }


}
