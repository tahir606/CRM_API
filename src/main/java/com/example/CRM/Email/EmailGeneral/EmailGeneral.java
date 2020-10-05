package com.example.CRM.Email.EmailGeneral;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "email_general")
public class EmailGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_No")
    private int emailNo;
    @Column(name = "Message_No")
    private int messageNo;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "TO_Address")
    private String toAddress;
    @Column(name = "FROM_Address")
    private String fromAddress;
    @Column(name = "CC_Address")
    private String ccAddress;
    @Column(name = "BCC_Address")
    private String bccAddress;
    @Column(name = "Email_Body")
    private String body;
    @Column(name = "Attach")
    private String attachment;
    @Column(name = "TimesTamp")
    private Date timesTamp;
    @Column(name = "Freeze")
    private int freeze;
    public EmailGeneral(){

    }

    public EmailGeneral(int emailNo, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                        String body, String attachment, Date timesTamp, int freeze) {
        this.emailNo = emailNo;
        this.messageNo = messageNo;
        this.subject = subject;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.ccAddress = ccAddress;
        this.bccAddress = bccAddress;
        this.body = body;
        this.attachment = attachment;
        this.timesTamp = timesTamp;
        this.freeze = freeze;
    }

    public int getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(int emailNo) {
        this.emailNo = emailNo;
    }

    public int getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(int messageNo) {
        this.messageNo = messageNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(Date timesTamp) {
        this.timesTamp = timesTamp;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "Email_General{" +
                "emailNo=" + emailNo +
                ", messageNo=" + messageNo +
                ", subject='" + subject + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", bccAddress='" + bccAddress + '\'' +
                ", emailBody='" + body + '\'' +
                ", attachment='" + attachment + '\'' +
                ", timesTamp=" + timesTamp +
                ", freeze=" + freeze +
                '}';
    }
}
