package com.example.CRM.Note;

import javax.persistence.*;
import java.sql.Date;

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
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreatedOn")
    private Date createdOn;
    @Column(name = "N_Id")
    private int nID;

    public Note(){

    }

    public Note(int noteCode, String text, int contactID, int clientID, int psID, int leadsId, int createdBy, Date createdOn, int nID) {
        this.noteCode = noteCode;
        this.text = text;
        this.contactID = contactID;
        this.clientID = clientID;
        this.psID = psID;
        this.leadsId = leadsId;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.nID = nID;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getnID() {
        return nID;
    }

    public void setnID(int nID) {
        this.nID = nID;
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
                ", nID=" + nID +
                '}';
    }
}
