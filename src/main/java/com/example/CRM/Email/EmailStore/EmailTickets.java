package com.example.CRM.Email.EmailStore;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "email_store")
public class EmailTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMNO")
    private int emailNo;
    @Column(name = "MSGNO")
    private int messageNo;
    @Column(name = "SBJCT")
    private String subject;
    @Column(name = "TOADD")
    private String toAddress;
    @Column(name = "FRADD")
    private String fromAddress;
    @Column(name = "EBODY")
    private String emailBody;
    @Column(name = "ATTCH")
    private String attachment;
    @Column(name = "CCADD")
    private String ccAddress;
    @Column(name = "ESOLV")
    private char emailSolve;
    @Column(name = "SOLVBY")
    private int emailSolveBy = 0;
    @Column(name = "SOLVTIME")
    private Date solveTime;
    @Column(name = "LOCKD")
    private int emailLocked;
    @Column(name = "TSTMP")
    private Date timesTamp;
    @Column(name = "MANUAL")
    private int manualEmail = 0;
    @Column(name = "LOCKTIME")
    private Date lockTime;
    @Column(name = "FREZE")
    private int freeze;

    public EmailTickets() {

    }

    public EmailTickets(int emailNo, int messageNo, String subject, String toAddress, String fromAddress,
                        String emailBody, String attachment, String ccAddress, char emailSolve,
                        int emailSolveBy, Date solveTime, int emailLocked, Date timesTamp, int manualEmail,
                        Date lockTime, int freeze) {
        this.emailNo = emailNo;
        this.messageNo = messageNo;
        this.subject = subject;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.emailBody = emailBody;
        this.attachment = attachment;
        this.ccAddress = ccAddress;
        this.emailSolve = emailSolve;
        this.emailSolveBy = emailSolveBy;
        this.solveTime = solveTime;
        this.emailLocked = emailLocked;
        this.timesTamp = timesTamp;
        this.manualEmail = manualEmail;
        this.lockTime = lockTime;
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

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public char getEmailSolve() {
        return emailSolve;
    }

    public void setEmailSolve(char emailSolve) {
        this.emailSolve = emailSolve;
    }

    public int getEmailSolveBy() {
        return emailSolveBy;
    }

    public void setEmailSolveBy(int emailSolveBy) {
        this.emailSolveBy = emailSolveBy;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public int getEmailLocked() {
        return emailLocked;
    }

    public void setEmailLocked(int emailLocked) {
        this.emailLocked = emailLocked;
    }

    public Date getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(Date timesTamp) {
        this.timesTamp = timesTamp;
    }

    public int getManualEmail() {
        return manualEmail;
    }

    public void setManualEmail(int manualEmail) {
        this.manualEmail = manualEmail;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
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
                "emailNo=" + emailNo +
                ", messageNo=" + messageNo +
                ", subject='" + subject + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", attachment='" + attachment + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", emailSolve=" + emailSolve +
                ", emailSolveBy=" + emailSolveBy +
                ", solveTime=" + solveTime +
                ", emailLocked=" + emailLocked +
                ", timesTamp=" + timesTamp +
                ", manualEmail=" + manualEmail +
                ", lockTime=" + lockTime +
                ", freeze=" + freeze +
                '}';
    }
}
