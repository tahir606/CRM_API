package com.example.CRM.JCode;

import com.example.CRM.Domain.DomainListRepository;
import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailGeneral.EmailGeneralRepository;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailSent.EmailSentRepository;
import com.example.CRM.Email.EmailTicket.EmailNotFoundException;
import com.example.CRM.Email.EmailTicket.EmailTicketCustomQueryRepository;
import com.example.CRM.Email.EmailTicket.EmailTicketsRepository;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.Email.History.HistoryRepository;
import com.example.CRM.Email.History.TicketHistory;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.Email.Setiings.SettingRepository;
import com.example.CRM.Note.Note;
import com.example.CRM.Note.NoteNotFoundException;
import com.example.CRM.Note.NoteRepository;
import com.example.CRM.User.UserNotFoundException;
import com.example.CRM.User.UserRepository;
import com.example.CRM.User.Users;
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
    private final EmailTicketCustomQueryRepository emailTicketCustomQueryRepository;
    private final HistoryRepository historyRepository;
    private final NoteRepository noteRepository;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public EmailDBHandler(EmailTicketsRepository emailTicketsRepository, SettingRepository settingRepository, EmailSentRepository emailSentRepository, EmailGeneralRepository emailGeneralRepository, DomainListRepository domainListRepository, UserRepository userRepository, EmailTicketCustomQueryRepository emailTicketCustomQueryRepository, HistoryRepository historyRepository, NoteRepository noteRepository) {
        this.emailTicketsRepository = emailTicketsRepository;
        this.settingRepository = settingRepository;
        this.emailSentRepository = emailSentRepository;
        this.emailGeneralRepository = emailGeneralRepository;
        this.domainListRepository = domainListRepository;
        this.userRepository = userRepository;
        this.emailTicketCustomQueryRepository = emailTicketCustomQueryRepository;
        this.historyRepository = historyRepository;
        this.noteRepository = noteRepository;
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

    public EmailTickets getMaxTicketNo(){
        return emailTicketsRepository.findFirstByOrderByTicketNoDesc();
    }

    public TicketHistory insertHistory(TicketHistory ticketHistory) {
        return historyRepository.save(ticketHistory);
    }

    public Note insertNotes(Note note) {
        return noteRepository.save(note);
    }
    public Note findNote(int noteId){
        return noteRepository.findById(noteId)//
                .orElseThrow(() -> new NoteNotFoundException(noteId));
    }
    public List<EmailTickets> filteredEmails(String filter) {
        return emailTicketCustomQueryRepository.getFilterEmail(filter);
    }

    public int maxTicketNo() {
       int ticketNo =emailTicketsRepository.findFirstByOrderByTicketNoDesc().getTicketNo()+1;
        return ticketNo;
    }

    public EmailTickets searchEmail(int code , int freeze) {
        return emailTicketsRepository.findByTicketNoAndFreeze( code,freeze);
    }

    public EmailTickets findSelectedEmail(int code) {
        return emailTicketsRepository.findById(code)//
        .orElseThrow(()-> new EmailNotFoundException(code)) ;
    }
    public EmailGeneral findSelectedGeneralEmail(int code) {
        return emailGeneralRepository.findById(code)//
                .orElseThrow(() -> new EmailNotFoundException(code));
    }

    public EmailSent findSelectedSentEmail(int code) {
        return emailSentRepository.findById(code)//
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
        return emailSentRepository.findBySentOrderByCodeDesc(sent);
    }

    public List<EmailGeneral> readAllEmailsGeneral(int freeze) {
        return emailGeneralRepository.findAllByFreezeOrderByCodeDesc(freeze);
    }

    public List<EmailTickets> readAllEmailsStore() {
        return emailTicketsRepository.findAll();
    }

//    public List<EmailTickets> readAllSolvedEmails(char solve) {
//        return emailTicketsRepository.findBySolved(solve);
//    }

//    public List<EmailTickets> readAllLockedEmails() {
//        return emailTicketsRepository.findByLocked(1);
//    }
//

//    public int getNoOfLockedUnlockedEmailsByUser(int locked, int solvedBy) {
//        if (locked == 1) {
//            return emailTicketsRepository.countByLockedAndSolvedBy(locked, solvedBy);
//        } else {
//            return emailTicketsRepository.countByLockedAndSolvedBy(locked, solvedBy);
//        }
//    }

//    public int getLatestEmailNo() {
//        return ticketsRepository.findTopByOrderByCodeDesc();
//    }

//    public int getNoOfSolvedEmailsByUser(char solve, int solveBy) {
//        if (solve == 'Y') {
//            return emailTicketsRepository.countBySolvedAndSolvedBy(solve, solveBy);
//        } else {
//            return emailTicketsRepository.countBySolvedAndSolvedBy(solve, solveBy);
//        }
//    }

}
