package com.example.CRM.Note;

import com.example.CRM.Client.Client;
import com.example.CRM.Contact.Contact;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "note_store")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "noteCode",
//        scope     = Integer.class)
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Note_Code")
    private int noteCode;
    @Column(name = "Note_Text")
    private String text;

    @Column(name = "Product_ID")
    private int psID;
    @Column(name = "Leads_ID")
    private int leadsId;
    @Column(name = "Created_On")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;
    @Column(name = "Created_By")
    private Integer createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Created_By", insertable = false, updatable = false)
    private Users users;

    @Column(name = "EmailId")
    private Integer emailId;
    @JsonBackReference(value = "eNoteList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmailId", insertable = false, updatable = false)
    private EmailTickets emailTickets;

    @Column(name = "CS_ID")
    private Integer contactID;
    @JsonBackReference(value = "coNoteList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CS_ID", insertable = false, updatable = false)
    private Contact contactNoteList;

    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clNoteList")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientNoteList;

    public Note() {

    }

    public Note(int noteCode, String text, int contactID, int clientID, int psID, int leadsId, int createdBy, Users users, Timestamp createdOn, int freeze, int emailId, EmailTickets emailTickets) {
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

    public Contact getContactNoteList() {
        return contactNoteList;
    }

    public void setContactNoteList(Contact contact) {
        this.contactNoteList = contact;
    }

    public Client getClientNoteList() {
        return clientNoteList;
    }

    public void setClientNoteList(Client client) {
        this.clientNoteList = client;
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

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
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

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailID) {
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
