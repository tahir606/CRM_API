package com.example.CRM.Client;

import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.Event.Event;
import com.example.CRM.LeadStore.Lead;
import com.example.CRM.Note.Note;
import com.example.CRM.Phone.PhoneList;
import com.example.CRM.Task.Task;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "client_store")
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "clientID",
//        scope     = Integer.class)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CL_ID")
    private int clientID;
    @Column(name = "Cl_Name")
    private String name;
    @Column(name = "Cl_Owner")
    private String owner;
    @Column(name = "Cl_Email")
    private String email;
    @Column(name = "Cl_Phone")
    private String phoneNo;
    @Column(name = "Cl_Address")
    private String address;
    @Column(name = "Cl_City")
    private String city;
    @Column(name = "Cl_Country")
    private String country;
    @Column(name = "Cl_Website")
    private String website;
    @Column(name = "Cl_JoinDate")
    private Timestamp joinDate;
    @Column(name = "Cl_Bcycle")
    private Timestamp bicycle;
    @Column(name = "Cl_Type")
    private int type;

    @Column(name = "Leads_ID")
    private Integer fromLead;
    @JsonBackReference(value = "ldClientList")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Leads_ID", insertable = false, updatable = false)
    private Lead leadsClientList;

    @Column(name = "availableCount")
    private Long availableCount;

    @Column(name = "Created_By")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Created_By", insertable = false, updatable = false)
    private Users users;

    @Column(name = "Created_On")
    private Timestamp createdOn;

    @JsonManagedReference(value = "clEmailLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientEmailList")
    private List<EmailList> clEmailLists;

    @JsonManagedReference(value = "clPhoneLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientPhoneList")
    private List<PhoneList> clPhoneLists;

    @JsonManagedReference(value = "clNoteList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientNoteList")
    private List<Note> clNoteList;

    @JsonManagedReference(value = "clTaskList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientTaskList")
    private List<Task> clTaskList;

    @JsonManagedReference(value = "clEventList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clientEventList")
    private List<Event> clEventList;

    public Client() {

    }

    public Client(int clientID, String name, String owner, long availableCount) {
        this.clientID = clientID;
        this.name = name;
        this.owner = owner;
        this.availableCount = availableCount;
    }



    public Lead getLeadsClientList() {
        return leadsClientList;
    }

    public void setLeadsClientList(Lead leadsClientList) {
        this.leadsClientList = leadsClientList;
    }

    public Long getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Long availableCount) {
        this.availableCount = availableCount;
    }

    public List<Task> getClTaskList() {
        return clTaskList;
    }

    public void setClTaskList(List<Task> clTaskList) {
        this.clTaskList = clTaskList;
    }

    public List<Event> getClEventList() {
        return clEventList;
    }

    public void setClEventList(List<Event> clEventList) {
        this.clEventList = clEventList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Note> getClNoteList() {
        return clNoteList;
    }

    public void setClNoteList(List<Note> noteList) {
        this.clNoteList = noteList;
    }

    public List<EmailList> getClEmailLists() {
        return clEmailLists;
    }

    public void setClEmailLists(List<EmailList> emailLists) {
        this.clEmailLists = emailLists;
    }

    public List<PhoneList> getClPhoneLists() {
        return clPhoneLists;
    }

    public void setClPhoneLists(List<PhoneList> phoneLists) {
        this.clPhoneLists = phoneLists;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public Timestamp getBicycle() {
        return bicycle;
    }

    public void setBicycle(Timestamp bicycle) {
        this.bicycle = bicycle;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getFromLead() {
        return fromLead;
    }

    public void setFromLead(Integer fromLead) {
        this.fromLead = fromLead;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", website='" + website + '\'' +
                ", joinDate=" + joinDate +
                ", bicycle=" + bicycle +
                ", type=" + type +
                ", fromLead=" + fromLead +
                ", leadsClientList=" + leadsClientList +
                ", availableCount=" + availableCount +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", createdOn=" + createdOn +
                ", clEmailLists=" + clEmailLists +
                ", clPhoneLists=" + clPhoneLists +
                ", clNoteList=" + clNoteList +
                ", clTaskList=" + clTaskList +
                ", clEventList=" + clEventList +
                '}';
    }
}
