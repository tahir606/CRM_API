package com.example.CRM.Email.EmailTicket;


import com.example.CRM.Email.Email;
import com.example.CRM.Note.Note;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "email_ticket")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ticketNo"})
})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "ticketNo",
//        scope     = Integer.class)
public class EmailTickets extends Email implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketNo")
    private int ticketNo;
    @Column(name = "manual")
    private int manualEmail = 6;
    @Column(name = "sendAsEmail")
    private boolean sendAsEmail = false;
    @Column(name = "isAllocatedBy")
    private int isAllocatedBy = 0;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.UNLOCKED;
    @Column(name = "lockedTime")
    private Timestamp lockedTime;
    @Column(name = "solvedTime")
    private Timestamp solvedTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manual", insertable = false, updatable = false)
    private Users users;

    @JsonManagedReference(value = "eNoteList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emailTickets")
    private List<Note> eNoteList;

    public EmailTickets() {

    }

    public EmailTickets(int code, int messageNo, String subject, List<String> toAddress, List<String> fromAddress, List<String> ccAddress, List<String> bccAddress,
                        String body, List<String> attachment, Timestamp timestamp, int freeze, int manualEmail, Status status, Users users, int ticketNo) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress, bccAddress, body, attachment, timestamp, freeze);
        this.ticketNo = ticketNo;
        this.manualEmail = manualEmail;
        this.status = status;
        this.users = users;
    }
public EmailTickets(int code,String subject,List<String> fromAddress,java.util.Date timestamp,java.util.Date lockedTime ,java.util.Date solvedTime){
        super(code,fromAddress,subject,new Timestamp(timestamp.getTime()));
    if (lockedTime != null) {

        this.lockedTime = new Timestamp(lockedTime.getTime());
    }

    if (solvedTime != null) {
        this.solvedTime = new Timestamp(solvedTime.getTime());
    }
}
    public EmailTickets(int ticketNo, int code, String subject,List<String> fromAddress,  java.util.Date timestamp, java.util.Date lockedTime, java.util.Date solvedTime) {
        super(code, fromAddress, subject, new Timestamp(timestamp.getTime()));
       if (lockedTime != null) {

            this.lockedTime = new Timestamp(lockedTime.getTime());
        }

        if (solvedTime != null) {
            this.solvedTime = new Timestamp(solvedTime.getTime());
        }

        this.ticketNo = ticketNo;
    }

    public Timestamp getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Timestamp lockedTime) {
        this.lockedTime = lockedTime;
    }

    public List<Note> geteNoteList() {
        return eNoteList;
    }

    public void seteNoteList(List<Note> eNoteList) {
        this.eNoteList = eNoteList;
    }

    public Timestamp getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Timestamp solvedTime) {
        this.solvedTime = solvedTime;
    }



    public boolean isSendAsEmail() {
        return sendAsEmail;
    }

    public void setSendAsEmail(boolean sendAsEmail) {
        this.sendAsEmail = sendAsEmail;
    }

    public int getIsAllocatedBy() {
        return isAllocatedBy;
    }

    public void setIsAllocatedBy(int isAllocatedBy) {
        this.isAllocatedBy = isAllocatedBy;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }


    public int getManualEmail() {
        return manualEmail;
    }

    public void setManualEmail(int manualEmail) {
        this.manualEmail = manualEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "EmailTickets{" +
                "ticketNo =" + ticketNo +
                "manualEmail =" + manualEmail +
                ", status =" + status +
                ", users =" + users +
                '}';
    }

}
