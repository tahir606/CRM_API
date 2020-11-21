package com.example.CRM.Email.Setiings;

import org.springframework.hateoas.EntityModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "email_settings")
public class EmailSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Email_Code")
    private int code;
    @Column(name = "Host")
    private String host;
    @Column(name = "Email")
    private String email;
    @Column(name = "Pass")
    private String password;
    @Column(name = "File_Path")
    private String filePath;
    @Column(name = "General_Email")
    private String generaEmail;
    @Column(name = "Auto_Check")
    private int autoCheck;
    @Column(name="Disclaimer_Check")
    private int disclaimerCheck;
    @Column(name = "auto_text")
    private String autoText;
    @Column(name = "Disclaimer_Text")
    private String disclaimerText;
    @Column(name = "Solve_Check")
    private int solveCheck;
    @Column(name = "Solve_Text")
    private String solveText;
    @Column(name = "Replacement_Keyword")
    private String replacementKeyword;

    public EmailSettings(){

    }
    public EmailSettings(int code, String host, String email, String password, String filePath, String generaEmail,
                         int autoCheck, int disclaimerCheck, String autoText, String disclaimerText, int solveCheck,
                         String solveText, String replacementKeyword) {
        this.code = code;
        this.host = host;
        this.email = email;
        this.password = password;
        this.filePath = filePath;
        this.generaEmail = generaEmail;
        this.autoCheck = autoCheck;
        this.disclaimerCheck = disclaimerCheck;
        this.autoText = autoText;
        this.disclaimerText = disclaimerText;
        this.solveCheck = solveCheck;
        this.solveText = solveText;
        this.replacementKeyword = replacementKeyword;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getGeneraEmail() {
        return generaEmail;
    }

    public void setGeneraEmail(String generaEmail) {
        this.generaEmail = generaEmail;
    }

    public int getAutoCheck() {
        return autoCheck;
    }

    public void setAutoCheck(int autoCheck) {
        this.autoCheck = autoCheck;
    }

    public int getDisclaimerCheck() {
        return disclaimerCheck;
    }

    public void setDisclaimerCheck(int disclaimerCheck) {
        this.disclaimerCheck = disclaimerCheck;
    }

    public String getAutoText() {
        return autoText;
    }

    public void setAutoText(String autoText) {
        this.autoText = autoText;
    }

    public String getDisclaimerText() {
        return disclaimerText;
    }

    public void setDisclaimerText(String disclaimerText) {
        this.disclaimerText = disclaimerText;
    }

    public int getSolveCheck() {
        return solveCheck;
    }

    public void setSolveCheck(int solveCheck) {
        this.solveCheck = solveCheck;
    }

    public String getSolveText() {
        return solveText;
    }

    public void setSolveText(String solveText) {
        this.solveText = solveText;
    }

    public String getReplacementKeyword() {
        return replacementKeyword;
    }

    public void setReplacementKeyword(String replacementKeyword) {
        this.replacementKeyword = replacementKeyword;
    }

}
