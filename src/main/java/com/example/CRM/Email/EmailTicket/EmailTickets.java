package com.example.CRM.Email.EmailTicket;


import com.example.CRM.Email.Email;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "email_ticket")
public class EmailTickets extends Email {


    @Column(name = "solved")
    private char solved;
    @Column(name = "solvedBy")
    private int solvedBy;
    @Column(name = "solvedTime")
    private Timestamp solveTime;
    @Column(name = "locked")
    private int locked=0;
    @Column(name = "manual")
    private int manualEmail = 0;
    @Column(name = "lockTime")
    private Timestamp lockTime;

    public EmailTickets() {

    }

    public EmailTickets(int code, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                        String body, String attachment, Timestamp timestamp, int freeze, char solved, int solvedBy, Timestamp solveTime,
                        int locked, int manualEmail, Timestamp lockTime) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress, bccAddress, body, attachment, timestamp, freeze);
        this.solved = solved;
        this.solvedBy = solvedBy;
        this.solveTime = solveTime;
        this.locked = locked;
        this.manualEmail = manualEmail;
        this.lockTime = lockTime;
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

    public Timestamp getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Timestamp solveTime) {
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

    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime(Timestamp lockTime) {
        this.lockTime = lockTime;
    }

    @Override
    public String toString() {
        return "EmailTickets{" +
                "solved=" + solved +
                ", solvedBy=" + solvedBy +
                ", solveTime=" + solveTime +
                ", locked=" + locked +
                ", manualEmail=" + manualEmail +
                ", lockTime=" + lockTime +
                '}';
    }
}
