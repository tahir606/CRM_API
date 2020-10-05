package com.example.CRM.Email.EmailSent;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "email_sent")
public class EmailSent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_No")
    private int emailNo;
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
    @Column(name = "Upload_Attach")
    private char upload_Attach;
    @Column(name = "TimesTamp")
    private Date timesTamp;
    @Column(name = "user_Code")
    private int userCode;
    @Column(name = "ESNO")
    private int esno;
    @Column(name = "Sent")
    private int sent ;
    @Column(name = "Freeze")
    private int freeze;

    public EmailSent(){

    }
    public EmailSent(int emailNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress, String body,
                     String attachment, char upload_Attach, Date timesTamp, int userCode, int esno, int sent, int freeze) {
        this.emailNo = emailNo;
        this.subject = subject;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.ccAddress = ccAddress;
        this.bccAddress = bccAddress;
        this.body = body;
        this.attachment = attachment;
        this.upload_Attach = upload_Attach;
        this.timesTamp = timesTamp;
        this.userCode = userCode;
        this.esno = esno;
        this.sent = sent;
        this.freeze = freeze;
    }

    public int getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(int emailNo) {
        this.emailNo = emailNo;
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

    public char getUpload_Attach() {
        return upload_Attach;
    }

    public void setUpload_Attach(char upload_Attach) {
        this.upload_Attach = upload_Attach;
    }

    public Date getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(Date timesTamp) {
        this.timesTamp = timesTamp;
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

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "Email_Sent{" +
                "emailNo=" + emailNo +
                ", subject='" + subject + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", bccAddress='" + bccAddress + '\'' +
                ", emailBody='" + body + '\'' +
                ", attachment='" + attachment + '\'' +
                ", upload_Attach=" + upload_Attach +
                ", timesTamp=" + timesTamp +
                ", userCode=" + userCode +
                ", esno=" + esno +
                ", sent=" + sent +
                ", freeze=" + freeze +
                '}';
    }
}
