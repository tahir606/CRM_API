package com.example.CRM.Note;

import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.User.Users;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "note_store")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Note_Code")
    private int noteCode;
    @Column(name = "Note_Text")
    private String text;
    @Column(name = "Contact_ID")
    private int contactID;
    @Column(name = "Client_ID")
    private int clientID;
    @Column(name = "Product_ID")
    private int psID;
    @Column(name = "Leads_ID")
    private int leadsId;
    @Column(name = "Created_On")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;
    @Column(name = "Created_By")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Created_By", insertable = false, updatable = false)
    private Users users;

    @Column(name = "EmailId")
    private int emailId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmailId", insertable = false, updatable = false)
    private EmailTickets emailTickets;


    public Note() {

    }

    public Note(int noteCode, String text, int contactID, int clientID, int psID, int leadsId, int createdBy, Users users, Timestamp createdOn,int freeze, int emailId, EmailTickets emailTickets) {
        this.noteCode = noteCode;
        this.text = text;
        this.contactID = contactID;
        this.clientID = clientID;
        this.psID = psID;
        this.leadsId = leadsId;
        this.createdOn = createdOn;
        this.freeze = freeze;
        this.createdBy = createdBy;
        this.users = users;
        this.emailId = emailId;
        this.emailTickets = emailTickets;
    }

    public int getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(int noteCode) {
        this.noteCode = noteCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getPsID() {
        return psID;
    }

    public void setPsID(int psID) {
        this.psID = psID;
    }

    public int getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(int leadsId) {
        this.leadsId = leadsId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailID) {
        this.emailId = emailID;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public EmailTickets getEmailTickets() {
        return emailTickets;
    }

    public void setEmailTickets(EmailTickets emailTickets) {
        this.emailTickets = emailTickets;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteCode=" + noteCode +
                ", noteText='" + text + '\'' +
                ", contactID=" + contactID +
                ", clientID=" + clientID +
                ", psID=" + psID +
                ", leadsId=" + leadsId +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", emailID=" + emailId +
                ", freeze=" + freeze +
                '}';
    }
}
