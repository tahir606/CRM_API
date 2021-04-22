package com.example.CRM.keyword;

import javax.persistence.*;

@Entity(name = "keyword_List")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    @Column(name = "code")
    private int code;
    @Column(name = "keywordName")
    private String keywordName;

    public Keyword() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "code=" + code +
                ", keywordName='" + keywordName + '\'' +
                '}';
    }
}
