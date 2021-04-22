package com.example.CRM.Email.History;

import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.Email.EmailTicket.Status;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "history")
public class TicketHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hCode")
    private int hCode;
    @Column(name = "userId")
    private int userId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users users;
    @Column(name = "emailId")
    private int emailId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emailId", insertable = false, updatable = false)
    private EmailTickets emailTickets;
    @Column(name = "time")
    private Timestamp time;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public TicketHistory() {
    }

    public TicketHistory(int hCode, int userId, Users users, int emailId, EmailTickets emailTickets, Timestamp time, Status status) {
        this.hCode = hCode;
        this.userId = userId;
        this.users = users;
        this.emailId = emailId;
        this.emailTickets = emailTickets;
        this.time = time;
        this.status = status;
    }

    public int gethCode() {
        return hCode;
    }

    public void sethCode(int hCode) {
        this.hCode = hCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getCode() {
        return emailId;
    }

    public void setCode(int emailId) {
        this.emailId = emailId;
    }



    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketHistory{" +
                "hCode=" + hCode +
                ", userId=" + userId +
                ", users=" + users +
                ", code=" + emailId +
//                ", emailTickets=" + emailTickets +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
