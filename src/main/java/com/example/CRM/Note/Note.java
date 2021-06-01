package com.example.CRM.Note;

import com.example.CRM.Client.Client;
import com.example.CRM.Contact.Contact;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.LeadStore.Lead;
import com.example.CRM.Product.Store.Product;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "note_store")
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
    @Column(name = "Created_On")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;

    @Column(name = "Created_By")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Created_By", insertable = false, updatable = false)
    private Users users;

    @Column(name = "Product_ID")
    private Integer psID;
    @JsonBackReference(value = "pdNoteList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_ID", insertable = false, updatable = false)
    private Product productNoteList;

    @Column(name = "Leads_ID")
    private Integer leadsId;
    @JsonBackReference(value = "ldNoteList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Leads_ID", insertable = false, updatable = false)
    private Lead leadsNoteList;

    @Column(name = "EmailId")
    private Integer emailId;
    @JsonBackReference(value = "eNoteList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmailId", insertable = false, updatable = false)
    private EmailTickets emailTickets;

    @Column(name = "CS_ID")
    private Integer contactID;
    @JsonBackReference(value = "coNoteList")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CS_ID", insertable = false, updatable = false)
    private Contact contactNoteList;

    @Column(name = "CL_ID")
    private Integer clientID;
    @JsonBackReference(value = "clNoteList")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client clientNoteList;

    public Note() {

    }

    public Integer getPsID() {
        return psID;
    }

    public void setPsID(Integer psID) {
        this.psID = psID;
    }

    public Integer getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(Integer leadsId) {
        this.leadsId = leadsId;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
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

    public Product getProductNoteList() {
        return productNoteList;
    }

    public void setProductNoteList(Product productNoteList) {
        this.productNoteList = productNoteList;
    }

    public Lead getLeadsNoteList() {
        return leadsNoteList;
    }

    public void setLeadsNoteList(Lead leadsNoteList) {
        this.leadsNoteList = leadsNoteList;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }


    @Override
    public String toString() {
        return "Note{" +
                "noteCode=" + noteCode +
                ", text='" + text + '\'' +
                ", createdOn=" + createdOn +
                ", freeze=" + freeze +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", psID=" + psID +
                ", productNoteList=" + productNoteList +
                ", leadsId=" + leadsId +
                ", leadsNoteList=" + leadsNoteList +
                ", emailId=" + emailId +
                ", emailTickets=" + emailTickets +
                ", contactID=" + contactID +
                ", contactNoteList=" + contactNoteList +
                ", clientID=" + clientID +
                ", clientNoteList=" + clientNoteList +
                '}';
    }
}
