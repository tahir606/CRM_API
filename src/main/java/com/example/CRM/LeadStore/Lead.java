package com.example.CRM.LeadStore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "leads_store")
public class Lead {
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
    @Column(name = "s_ID")
    private int sId;
    @Column(name = "s_Other")
    private String sOther;
    @Column(name = "Converted")
    private int converted;
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreatedOn")
    private Date createdOn;
    @Column(name = "freeze")
    private int freeze;

    public Lead(){

    }

    public Lead(int leadsId, String firstName, String lastName, String tittle, String companyName,
                String website, String city, String country, String note, int sId, String sOther,
                int converted, int createdBy, Date createdOn, int freeze) {
        this.leadsId = leadsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tittle = tittle;
        this.companyName = companyName;
        this.website = website;
        this.city = city;
        this.country = country;
        this.note = note;
        this.sId = sId;
        this.sOther = sOther;
        this.converted = converted;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.freeze = freeze;
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

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "LeadStore{" +
                "leads_id=" + leadsId +
                ", leads_firstName='" + firstName + '\'' +
                ", leads_lastName='" + lastName + '\'' +
                ", leads_tittle='" + tittle + '\'' +
                ", leads_companyName='" + companyName + '\'' +
                ", leads_website='" + website + '\'' +
                ", leads_city='" + city + '\'' +
                ", leads_country='" + country + '\'' +
                ", leads_note='" + note + '\'' +
                ", s_id=" + sId +
                ", s_other='" + sOther + '\'' +
                ", converted=" + converted +
                ", createdBy=" + createdBy +
                ", createDone=" + createdOn +
                ", freeze=" + freeze +
                '}';
    }
}
