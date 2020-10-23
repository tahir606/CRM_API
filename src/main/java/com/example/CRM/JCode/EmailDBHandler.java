package com.example.CRM.JCode;

import com.example.CRM.Domain.DomainListRepository;
import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailGeneral.EmailGeneralRepository;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailSent.EmailSentRepository;
import com.example.CRM.Email.EmailTicket.EmailNotFoundException;
import com.example.CRM.Email.EmailTicket.EmailTicketsRepository;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.Email.Setiings.SettingRepository;
import com.example.CRM.User.UserNotFoundException;
import com.example.CRM.User.UserRepository;
import com.example.CRM.User.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EmailDBHandler {
    private final EmailTicketsRepository emailTicketsRepository;
    private final SettingRepository settingRepository;
    private final EmailSentRepository emailSentRepository;
    private final EmailGeneralRepository emailGeneralRepository;
    private final DomainListRepository domainListRepository;
    private final UserRepository userRepository;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public EmailDBHandler(EmailTicketsRepository emailTicketsRepository, SettingRepository settingRepository, EmailSentRepository emailSentRepository, EmailGeneralRepository emailGeneralRepository, DomainListRepository domainListRepository, UserRepository userRepository) {
        this.emailTicketsRepository = emailTicketsRepository;
        this.settingRepository = settingRepository;
        this.emailSentRepository = emailSentRepository;
        this.emailGeneralRepository = emailGeneralRepository;
        this.domainListRepository = domainListRepository;
        this.userRepository = userRepository;
    }

    public Email insertEmail(Email email) {
        if (email instanceof EmailTickets) {
            return emailTicketsRepository.save((EmailTickets) email);
        } else if (email instanceof EmailSent) {
            ((EmailSent) email).setSent(1);
            return emailSentRepository.save((EmailSent) email);
        } else if (email instanceof EmailGeneral) {
            return emailGeneralRepository.save((EmailGeneral) email);
        }
        return null;
    }

    public EmailTickets findSelectedEmail(int code) {
        return emailTicketsRepository.findById(code)//
                .orElseThrow(() -> new EmailNotFoundException(code));
    }

    public Users getUser(int id) {
        return userRepository.findById(id)//
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<String> whiteListDomain(int wbList) {
        return domainListRepository.findNameByWhiteBlackList(wbList);
    }

    public EmailSettings getSettings() {
        return settingRepository.findAll().get(0);
    }

    public List<EmailSent> readAllEmailsSent(int sent) {
        return emailSentRepository.findBySent(sent);
    }

    public List<EmailGeneral> readAllEmailsGeneral() {
        return emailGeneralRepository.findAll();
    }

    public List<EmailTickets> readAllEmailsStore() {
        return emailTicketsRepository.findAll();
    }

    public List<EmailTickets> readAllSolvedEmails(char solve) {
        return emailTicketsRepository.findBySolved(solve);
    }

    public List<EmailTickets> readAllLockedEmails() {
        return emailTicketsRepository.findByLocked(1);
    }


    public int getNoOfLockedUnlockedEmailsByUser(int locked, int solvedBy) {
        if (locked == 1) {
            return emailTicketsRepository.countByLockedAndSolvedBy(locked, solvedBy);
        } else {
            return emailTicketsRepository.countByLockedAndSolvedBy(locked, solvedBy);
        }
    }

//    public int getLatestEmailNo() {
//        return ticketsRepository.findTopByOrderByCodeDesc();
//    }

    public int getNoOfSolvedEmailsByUser(char solve, int solveBy) {
        if (solve == 'Y') {
            return emailTicketsRepository.countBySolvedAndSolvedBy(solve, solveBy);
        } else {
            return emailTicketsRepository.countBySolvedAndSolvedBy(solve, solveBy);
        }
    }

}
