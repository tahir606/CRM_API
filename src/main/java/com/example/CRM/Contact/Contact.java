package com.example.CRM.Contact;

import com.example.CRM.Client.Client;
import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.Note.Note;
import com.example.CRM.Phone.PhoneList;
import com.example.CRM.User.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "contact_store")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property  = "contactID",
//        scope     = Integer.class)
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CS_ID")
    private int contactID;
    @Column(name = "CS_FirstName")
    private String firstName;
    @Column(name = "CS_LastName")
    private String lastName;
    @Column(name = "CS_DOB")
    private Timestamp dateOfBirth;
    @Column(name = "CS_Address")
    private String address;
    @Column(name = "CS_City")
    private String city;
    @Column(name = "CS_Country")
    private String country;
    @Column(name = "CS_Note")
    private String note;
    @Column(name = "CreatedOn")
    private Timestamp createdOn;
    @Column(name = "Freeze")
    private int freeze;

    @Column(name = "CL_ID")
    private int clientID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CL_ID", insertable = false, updatable = false)
    private Client client12;

    @Column(name = "Created_By")
    private int createdBy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Created_By", insertable = false, updatable = false)
    private Users users;

    @JsonManagedReference(value = "coEmailLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contactEmailList")
    private List<EmailList> coEmailLists;

    @JsonManagedReference(value = "coPhoneLists")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contactPhoneList")
    private List<PhoneList> coPhoneLists;

    @JsonManagedReference(value = "coNoteList")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contactNoteList")
    private List<Note> coNoteList;

    public Contact() {

    }

    public List<Note> getCoNoteList() {
        return coNoteList;
    }

    public void setCoNoteList(List<Note> noteList) {
        this.coNoteList = noteList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Client getClient12() {
        return client12;
    }

    public void setClient12(Client client) {
        this.client12 = client;
    }

    public List<EmailList> getCoEmailLists() {
        return coEmailLists;
    }

    public void setCoEmailLists(List<EmailList> emailLists) {
        this.coEmailLists = emailLists;
    }

    public List<PhoneList> getCoPhoneLists() {
        return coPhoneLists;
    }

    public void setCoPhoneLists(List<PhoneList> phoneLists) {
        this.coPhoneLists = phoneLists;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
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

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactID=" + contactID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", note='" + note + '\'' +
                ", createdOn=" + createdOn +
                ", freeze=" + freeze +
                ", clientID=" + clientID +
                ", client12=" + client12 +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", coEmailLists=" + coEmailLists +
                ", coPhoneLists=" + coPhoneLists +
                ", coNoteList=" + coNoteList +
                '}';
    }
}
