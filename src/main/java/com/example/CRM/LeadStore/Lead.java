package com.example.CRM.LeadStore;

import com.example.CRM.Client.Client;
import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.Event.Event;
import com.example.CRM.Note.Note;
import com.example.CRM.Phone.PhoneList;
import com.example.CRM.Source.Source;
import com.example.CRM.Task.Task;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "leads_store")
@Table(name = "leads_store")
public class Lead implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Leads_ID")
    private int leadsId;
    @Column(name = "leads_firstName")
    private String firstName;
    @Column(name = "leads_lastName")
    private String lastName;
    @Column(name = "leads_Tittle")
    private String tittle;
    @Column(name = "leads_companyName")
    private String companyName;
    @Column(name = "leads_website")
    private String website;
    @Column(name = "leads_City")
    private String city;
    @Column(name = "leads_Country")
    private String country;
    @Column(name = "leads_Note")
    private String note;

    @Column(name = "s_Other")
    private String sOther;
    @Column(name = "Converted")
    private int converted;

    @Column(name = "source_ID")
    private int sourceID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_ID", insertable = false, updatable = false)
    private Source source;

    @Column(name = "CreatedBy")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CreatedBy", insertable = false, updatable = false)
    private Users users;

    @JsonManagedReference(value = "ldClientList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsClientList")
    private List<Client> ldClientList;

    @JsonManagedReference(value = "ldEmailLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsEmailList")
    private List<EmailList> ldEmailLists;

    @JsonManagedReference(value = "ldPhoneLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsPhoneList")
    private List<PhoneList> ldPhoneLists;

    @JsonManagedReference(value = "ldNoteList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsNoteList")
    private List<Note> ldNoteList;

    @JsonManagedReference(value = "ldTaskList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsTaskList")
    private List<Task> ldTaskList;

    @JsonManagedReference(value = "ldEventList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "leadsEventList")
    private List<Event> ldEventList;


    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @Column(name = "freeze")
    private int freeze;

    public Lead(){

    }


    public List<Client> getLdClientList() {
        return ldClientList;
    }

    public void setLdClientList(List<Client> ldClientList) {
        this.ldClientList = ldClientList;
    }

    public int getLeadsId() {
        return leadsId;
    }

    public void setLeadsId(int leadsId) {
        this.leadsId = leadsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getsOther() {
        return sOther;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    public int getConverted() {
        return converted;
    }

    public void setConverted(int converted) {
        this.converted = converted;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<EmailList> getLdEmailLists() {
        return ldEmailLists;
    }

    public void setLdEmailLists(List<EmailList> ldEmailLists) {
        this.ldEmailLists = ldEmailLists;
    }

    public List<PhoneList> getLdPhoneLists() {
        return ldPhoneLists;
    }

    public void setLdPhoneLists(List<PhoneList> ldPhoneLists) {
        this.ldPhoneLists = ldPhoneLists;
    }

    public List<Note> getLdNoteList() {
        return ldNoteList;
    }

    public void setLdNoteList(List<Note> ldNoteList) {
        this.ldNoteList = ldNoteList;
    }

    public List<Task> getLdTaskList() {
        return ldTaskList;
    }

    public void setLdTaskList(List<Task> ldTaskList) {
        this.ldTaskList = ldTaskList;
    }

    public List<Event> getLdEventList() {
        return ldEventList;
    }

    public void setLdEventList(List<Event> ldEventList) {
        this.ldEventList = ldEventList;
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

//    @Override
//    public String toString() {
//        return "Lead{" +
//                "leadsId=" + leadsId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", tittle='" + tittle + '\'' +
//                ", companyName='" + companyName + '\'' +
//                ", website='" + website + '\'' +
//                ", city='" + city + '\'' +
//                ", country='" + country + '\'' +
//                ", note='" + note + '\'' +
//                ", sourceID=" + sourceID +
//                ", sOther='" + sOther + '\'' +
//                ", converted=" + converted +
//                ", createdBy=" + createdBy +
//                ", users=" + users +
//                ", ldEmailLists=" + ldEmailLists +
//                ", ldPhoneLists=" + ldPhoneLists +
//                ", ldNoteList=" + ldNoteList +
//                ", ldTaskList=" + ldTaskList +
//                ", ldEventList=" + ldEventList +
//                ", createdOn=" + createdOn +
//                ", freeze=" + freeze +
//                '}';
//    }
}
