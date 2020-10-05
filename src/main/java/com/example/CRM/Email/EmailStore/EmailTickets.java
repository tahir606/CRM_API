package com.example.CRM.Email.EmailStore;


import javax.mail.Address;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "email_store")
public class EmailTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMNO")
    private int code;
    @Column(name = "MSGNO")
    private int messageNo;
    @Column(name = "SBJCT")
    private String subject;
    @Column(name = "TOADD")
    private String toAddress;
    @Column(name = "FRADD")
    private String fromAddress;
    @Column(name = "EBODY")
    private String body;
    @Column(name = "ATTCH")
    private String attachment;
    @Column(name = "CCADD")
    private String ccAddress;
    @Column(name = "ESOLV")
    private char solved;
    @Column(name = "SOLVBY")
    private int solvedBy = 0;
    @Column(name = "SOLVTIME")
    private Date solveTime;
    @Column(name = "LOCKD")
    private int locked;
    @Column(name = "TSTMP")
    private String timesTamp;
    @Column(name = "MANUAL")
    private int manualEmail = 0;
    @Column(name = "LOCKTIME")
    private Date lockTime;
    @Column(name = "FREZE")
    private int freeze;

    public EmailTickets() {

    }

    public EmailTickets(int code, int messageNo, String subject, String toAddress, String fromAddress, String body, String attachment,
                        String ccAddress, char solved, int solvedBy, Date solveTime, int locked, String timesTamp, int manualEmail,
                        Date lockTime, int freeze) {
        this.code = code;
        this.messageNo = messageNo;
        this.subject = subject;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.body = body;
        this.attachment = attachment;
        this.ccAddress = ccAddress;
        this.solved = solved;
        this.solvedBy = solvedBy;
        this.solveTime = solveTime;
        this.locked = locked;
        this.timesTamp = timesTamp;
        this.manualEmail = manualEmail;
        this.lockTime = lockTime;
        this.freeze = freeze;
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

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
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

    public char getSolved() {
        return solved;
    }

    public void setSolved(char solved) {
        this.solved = solved;
    }

    public int getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(int solvedBy) {
        this.solvedBy = solvedBy;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
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
        return "EmailTickets{" +
                "code=" + code +
                ", messageNo=" + messageNo +
                ", subject='" + subject + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", body='" + body + '\'' +
                ", attachment='" + attachment + '\'' +
                ", ccAddress='" + ccAddress + '\'' +
                ", solved=" + solved +
                ", solvedBy=" + solvedBy +
                ", solveTime=" + solveTime +
                ", locked=" + locked +
                ", timesTamp=" + timesTamp +
                ", manualEmail=" + manualEmail +
                ", lockTime=" + lockTime +
                ", freeze=" + freeze +
                '}';
    }
}
