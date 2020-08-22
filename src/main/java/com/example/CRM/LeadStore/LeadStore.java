package com.example.CRM.LeadStore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "leads_store")
public class LeadStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Leads_ID")
    private int leads_id;
    @Column(name = "leads_firstName")
    private String leads_firstName;
    @Column(name = "leads_lastName")
    private String leads_lastName;
    @Column(name = "leads_Tittle")
    private String leads_tittle;
    @Column(name = "leads_companyName")
    private String leads_companyName;
    @Column(name = "leads_website")
    private String leads_website;
    @Column(name = "leads_City")
    private String leads_city;
    @Column(name = "leads_Country")
    private String leads_country;
    @Column(name = "leads_Note")
    private String leads_note;
    @Column(name = "s_ID")
    private int s_id;
    @Column(name = "s_Other")
    private String s_other;
    @Column(name = "Converted")
    private int converted;
    @Column(name = "CreatedBy")
    private int createdBy;
    @Column(name = "CreateDone")
    private Date createDone;
    @Column(name = "freeze")
    private int freeze;

    public LeadStore(){

    }

    public LeadStore(int leads_id, String leads_firstName, String leads_lastName, String leads_tittle, String leads_companyName,
                     String leads_website, String leads_city, String leads_country, String leads_note, int s_id, String s_other,
                     int converted, int createdBy, Date createDone, int freeze) {
        this.leads_id = leads_id;
        this.leads_firstName = leads_firstName;
        this.leads_lastName = leads_lastName;
        this.leads_tittle = leads_tittle;
        this.leads_companyName = leads_companyName;
        this.leads_website = leads_website;
        this.leads_city = leads_city;
        this.leads_country = leads_country;
        this.leads_note = leads_note;
        this.s_id = s_id;
        this.s_other = s_other;
        this.converted = converted;
        this.createdBy = createdBy;
        this.createDone = createDone;
        this.freeze = freeze;
    }

    public int getLeads_id() {
        return leads_id;
    }

    public void setLeads_id(int leads_id) {
        this.leads_id = leads_id;
    }

    public String getLeads_firstName() {
        return leads_firstName;
    }

    public void setLeads_firstName(String leads_firstName) {
        this.leads_firstName = leads_firstName;
    }

    public String getLeads_lastName() {
        return leads_lastName;
    }

    public void setLeads_lastName(String leads_lastName) {
        this.leads_lastName = leads_lastName;
    }

    public String getLeads_tittle() {
        return leads_tittle;
    }

    public void setLeads_tittle(String leads_tittle) {
        this.leads_tittle = leads_tittle;
    }

    public String getLeads_companyName() {
        return leads_companyName;
    }

    public void setLeads_companyName(String leads_companyName) {
        this.leads_companyName = leads_companyName;
    }

    public String getLeads_website() {
        return leads_website;
    }

    public void setLeads_website(String leads_website) {
        this.leads_website = leads_website;
    }

    public String getLeads_city() {
        return leads_city;
    }

    public void setLeads_city(String leads_city) {
        this.leads_city = leads_city;
    }

    public String getLeads_country() {
        return leads_country;
    }

    public void setLeads_country(String leads_country) {
        this.leads_country = leads_country;
    }

    public String getLeads_note() {
        return leads_note;
    }

    public void setLeads_note(String leads_note) {
        this.leads_note = leads_note;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_other() {
        return s_other;
    }

    public void setS_other(String s_other) {
        this.s_other = s_other;
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

    public Date getCreateDone() {
        return createDone;
    }

    public void setCreateDone(Date createDone) {
        this.createDone = createDone;
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
                "leads_id=" + leads_id +
                ", leads_firstName='" + leads_firstName + '\'' +
                ", leads_lastName='" + leads_lastName + '\'' +
                ", leads_tittle='" + leads_tittle + '\'' +
                ", leads_companyName='" + leads_companyName + '\'' +
                ", leads_website='" + leads_website + '\'' +
                ", leads_city='" + leads_city + '\'' +
                ", leads_country='" + leads_country + '\'' +
                ", leads_note='" + leads_note + '\'' +
                ", s_id=" + s_id +
                ", s_other='" + s_other + '\'' +
                ", converted=" + converted +
                ", createdBy=" + createdBy +
                ", createDone=" + createDone +
                ", freeze=" + freeze +
                '}';
    }
}
