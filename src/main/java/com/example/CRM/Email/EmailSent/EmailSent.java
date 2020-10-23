package com.example.CRM.Email.EmailSent;


import com.example.CRM.Email.Email;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "email_sent")
public class EmailSent extends Email {

    @Column(name = "UploadAttach")
    private char upload_Attach;
    @Column(name = "userCode")
    private int userCode;
    @Column(name = "ESNO")
    private int esno;
    @Column(name = "Sent")
    private int sent;

    public EmailSent() {

    }

    public EmailSent(int code, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                     String body, String attachment, Timestamp timestamp, int freeze, char upload_Attach, int userCode, int esno, int sent) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress, bccAddress, body, attachment, timestamp, freeze);
        this.upload_Attach = upload_Attach;
        this.userCode = userCode;
        this.esno = esno;
        this.sent = sent;
    }

    public char getUpload_Attach() {
        return upload_Attach;
    }

    public void setUpload_Attach(char upload_Attach) {
        this.upload_Attach = upload_Attach;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getEsno() {
        return esno;
    }

    public void setEsno(int esno) {
        this.esno = esno;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "EmailSent{" +
                "upload_Attach=" + upload_Attach +
                ", userCode=" + userCode +
                ", esno=" + esno +
                ", sent=" + sent +
                '}';
    }
}
