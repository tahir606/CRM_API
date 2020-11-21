package com.example.CRM.Email.EmailTicket;


import com.example.CRM.Email.Email;
import com.example.CRM.Email.History.TicketHistory;
import com.example.CRM.User.Users;

import javax.mail.Address;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "email_ticket")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ticketNo"})
})
public class EmailTickets extends Email implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketNo")
    private int ticketNo;
    @Column(name = "manual")
    private int manualEmail = 22;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.UNLOCKED;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manual", insertable = false, updatable = false)
    private Users users;
//    @OneToMany(mappedBy = "emailTickets", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<TicketHistory> history;


//    @Column(name = "solved")
//    private char solved='N';
//    @Column(name = "solved_by")
//    private int solvedBy;
//    @Column(name = "solved_time")
//    private Timestamp solveTime;
//    @Column(name = "locked")
//    private int locked=0;
//    @Column(name = "lock_time")
//    private Timestamp lockTime;

    public EmailTickets() {

    }

    public EmailTickets(int code, int messageNo, String subject, String toAddress, String fromAddress, String ccAddress, String bccAddress,
                        String body, String attachment, Timestamp timestamp, int freeze, int manualEmail, Status status, Users users, int ticketNo) {
        super(code, messageNo, subject, toAddress, fromAddress, ccAddress, bccAddress, body, attachment, timestamp, freeze);
        this.ticketNo = ticketNo;
        this.manualEmail = manualEmail;
        this.status = status;
        this.users = users;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }
//    public List<TicketHistory> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<TicketHistory> history) {
//        this.history = history;
//    }

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
    //    public char getSolved() {
//        return solved;
//    }
//
//    public void setSolved(char solved) {
//        this.solved = solved;
//    }
//
//    public int getSolvedBy() {
//        return solvedBy;
//    }
//
//    public void setSolvedBy(int solvedBy) {
//        this.solvedBy = solvedBy;
//    }
//
//    public Timestamp getSolveTime() {
//        return solveTime;
//    }
//
//    public void setSolveTime(Timestamp solveTime) {
//        this.solveTime = solveTime;
//    }
//
//    public int getLocked() {
//        return locked;
//    }
//
//    public void setLocked(int locked) {
//        this.locked = locked;
//    }
//
//    public Timestamp getLockTime() {
//        return lockTime;
//    }
//
//    public void setLockTime(Timestamp lockTime) {
//        this.lockTime = lockTime;
//    }

//    @Override
//    public String toString() {
//        return "EmailTickets{" +
//                "solved=" + solved +
//                ", solvedBy=" + solvedBy +
//                ", solveTime=" + solveTime +
//                ", locked=" + locked +
//                ", manualEmail=" + manualEmail +
//                ", lockTime=" + lockTime +
//                '}';
//    }
}
