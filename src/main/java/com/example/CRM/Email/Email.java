package com.example.CRM.Email;

import javax.mail.Address;
import javax.persistence.*;

import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.JCode.StringListConverter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
public abstract class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "email_no")
    private int code;

    @Column(name = "message_no")
    private int messageNo;

    @Column(name = "subject")
    private String subject;

    @Column(name = "to_address")
    @Convert(converter = StringListConverter.class)
    private List<String> toAddress;

    @Column(name = "from_address")
    @Convert(converter = StringListConverter.class)
    private List<String> fromAddress;

    @Column(name = "cc_address")
    @Convert(converter = StringListConverter.class)
    private List<String> ccAddress;

    @Column(name = "bcc_address")
    @Convert(converter = StringListConverter.class)
    private List<String> bccAddress;


    @Column(name = "body")
    private String body;

    @Column(name = "attachment")
    @Convert(converter = StringListConverter.class)
    private List<String> attachment;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Column(name = "freeze")
    private int freeze = 0;

    public Email() {
    }


    public Email(int code, int messageNo, String subject, List<String> toAddress, List<String> fromAddress, List<String> ccAddress, List<String> bccAddress,
                 String body, List<String> attachment, Timestamp timestamp, int freeze) {
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

    public Email(int code, List<String> fromAddress, String subject, Timestamp timestamp) {
        this.code = code;
        this.fromAddress = fromAddress;
        this.subject = subject;
        this.timestamp = timestamp;
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


    public List<String> getToAddress() {
        return toAddress;
    }

    public void setToAddress(List<String> toAddress) {
        this.toAddress = toAddress;
    }

    public List<String> getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(List<String> fromAddress) {
        this.fromAddress = fromAddress;
    }

    public List<String> getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(List<String> ccAddress) {
        this.ccAddress = ccAddress;
    }

    public List<String> getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(List<String> bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<String> attachment) {
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
