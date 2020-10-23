package com.example.CRM.Email;
import javax.persistence.*;

import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Id;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = EmailGeneral.class, name = "general"),
        @Type(value = EmailSent.class, name = "sent"),
        @Type(value = EmailTickets.class, name = "ticket"),
})
public abstract class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "emailNo")
    private int code;
    @Column(name = "messageNo")
    private int messageNo;
    @Column(name = "subject")
    private String subject;
    @Column(name = "toAddress")
    private String toAddress;
    @Column(name = "fromAddress")
    private String fromAddress;
    @Column(name = "ccAddress")
    private String ccAddress;
    @Column(name = "bccAddress")
    private String bccAddress;
    @Column(name = "body")
    private String body;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "Timestamp")
    private Timestamp timestamp;
    @Column(name = "freeze")
    private int freeze=0;
    public Email() {
    }


    public Email(int code, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                 String body, String attachment, Timestamp timestamp, int freeze) {
        this.code = code;
        this.messageNo = messageNo;
        this.subject = subject;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.ccAddress = ccAddress;
        this.bccAddress = bccAddress;
        this.body = body;
        this.attachment = attachment;
        this.timestamp = timestamp;
        this.freeze = freeze;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "Email{" +
                "code=" + code +
                ", messageNo=" + messageNo +
                ", subject='" + subject + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", bccAddress='" + bccAddress + '\'' +
                ", body='" + body + '\'' +
                ", attachment='" + attachment + '\'' +
                ", timestamp=" + timestamp +
                ", freeze=" + freeze +
                '}';
    }
}
