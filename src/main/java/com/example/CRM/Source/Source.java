package com.example.CRM.Source;

import javax.persistence.*;

@Entity
@Table(name = "source_list")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "source_ID")
    private int sourceID;
    @Column(name = "source_Name")
    private String name;
    @Column(name = "source_Description")
    private String description;

    public Source(){

    }
    public Source(int sourceID, String name, String description) {
        this.sourceID = sourceID;
        this.name = name;
        this.description = description;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Source{" +
                "source_ID=" + sourceID +
                ", source_Name='" + name + '\'' +
                ", source_Description=" + description +
                '}';
    }
}
