package com.example.CRM.JCode;

import com.example.CRM.Client.Client;
import com.example.CRM.Client.ClientCustomQueryRepository;
import com.example.CRM.Contact.Contact;
import com.example.CRM.Contact.ContactRepository;
import com.example.CRM.Domain.Domain;
import com.example.CRM.Domain.DomainListRepository;
import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailGeneral.EmailGeneral;
import com.example.CRM.Email.EmailGeneral.EmailGeneralRepository;
import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.Email.EmailList.EmailListCustomQuery;
import com.example.CRM.Email.EmailList.EmailListRepository;
import com.example.CRM.Email.EmailSent.EmailSent;
import com.example.CRM.Email.EmailSent.EmailSentRepository;
import com.example.CRM.Email.EmailTicket.*;
import com.example.CRM.Email.History.HistoryRepository;
import com.example.CRM.Email.History.TicketHistory;
import com.example.CRM.Email.Setiings.EmailSettings;
import com.example.CRM.Email.Setiings.SettingRepository;
import com.example.CRM.Note.Note;
import com.example.CRM.Note.NoteNotFoundException;
import com.example.CRM.Note.NoteRepository;
import com.example.CRM.Phone.PhoneListCustomQuery;
import com.example.CRM.Rights.RightsChart.RightChart;
import com.example.CRM.Rights.RightsChart.RightsChartCustomQueryRepository;
import com.example.CRM.Rights.RightsChart.RightsRepository;
import com.example.CRM.User.UserCustomQueryRepository;
import com.example.CRM.User.UserNotFoundException;
import com.example.CRM.User.UserRepository;
import com.example.CRM.User.Users;
import com.example.CRM.keyword.Keyword;
import com.example.CRM.keyword.KeywordRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    private final RightsRepository rightsRepository;
    private final RightsChartCustomQueryRepository rightsChartCustomQueryRepository;
    private final KeywordRepository keywordRepository;
    private final UserCustomQueryRepository userCustomQueryRepository;
    private final EmailListRepository emailListRepository;
    private final EmailListCustomQuery emailListCustomQuery;
    private final ContactRepository contactRepository;
    private final PhoneListCustomQuery phoneListCustomQuery;
    private final ClientCustomQueryRepository clientCustomQueryRepository;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public EmailDBHandler(EmailTicketsRepository emailTicketsRepository, SettingRepository settingRepository, EmailSentRepository emailSentRepository,
                          EmailGeneralRepository emailGeneralRepository, DomainListRepository domainListRepository, UserRepository userRepository,
                          EmailTicketCustomQueryRepository emailTicketCustomQueryRepository, HistoryRepository historyRepository,
                          NoteRepository noteRepository, RightsRepository rightsRepository, RightsChartCustomQueryRepository rightsChartCustomQueryRepository,
                          KeywordRepository keywordRepository, UserCustomQueryRepository userCustomQueryRepository, EmailListRepository emailListRepository, EmailListCustomQuery emailListCustomQuery, ContactRepository contactRepository, PhoneListCustomQuery phoneListCustomQuery, ClientCustomQueryRepository clientCustomQueryRepository) {
        this.emailTicketsRepository = emailTicketsRepository;
        this.settingRepository = settingRepository;
        this.emailSentRepository = emailSentRepository;
        this.emailGeneralRepository = emailGeneralRepository;
        this.domainListRepository = domainListRepository;
        this.userRepository = userRepository;
        this.emailTicketCustomQueryRepository = emailTicketCustomQueryRepository;
        this.historyRepository = historyRepository;
        this.noteRepository = noteRepository;
        this.rightsRepository = rightsRepository;
        this.rightsChartCustomQueryRepository = rightsChartCustomQueryRepository;
        this.keywordRepository = keywordRepository;
        this.userCustomQueryRepository = userCustomQueryRepository;
        this.emailListRepository = emailListRepository;
        this.emailListCustomQuery = emailListCustomQuery;
        this.contactRepository = contactRepository;
        this.phoneListCustomQuery = phoneListCustomQuery;
        this.clientCustomQueryRepository = clientCustomQueryRepository;
    }

    public Email insertEmail(Email email) {
        if (email instanceof EmailTickets) {
            return emailTicketsRepository.save((EmailTickets) email);
        } else if (email instanceof EmailSent) {
//            ((EmailSent) email).setSent(1);
            return emailSentRepository.save((EmailSent) email);
        } else if (email instanceof EmailGeneral) {
            return emailGeneralRepository.save((EmailGeneral) email);
        }
        return null;
    }

    public EmailTickets getMaxTicketNo() {
        return emailTicketsRepository.findFirstByOrderByTicketNoDesc();
    }

    public TicketHistory insertHistory(TicketHistory ticketHistory) {
        return historyRepository.save(ticketHistory);
    }

    public Note insertNotes(Note note) {
        return noteRepository.save(note);
    }

    public Note findNote(int noteId) {
        return noteRepository.findById(noteId)//
                .orElseThrow(() -> new NoteNotFoundException(noteId));
    }

    public List<EmailTickets> filteredEmails(String filter) {
        return emailTicketCustomQueryRepository.getFilterEmail(filter);
    }

    public int maxTicketNo() {
        int ticketNo = emailTicketsRepository.findFirstByOrderByTicketNoDesc().getTicketNo() + 1;
        return ticketNo;
    }

    public EmailTickets searchEmail(int code, int freeze) {
        return emailTicketsRepository.findByTicketNoAndFreeze(code, freeze);
    }

    public EmailTickets findSelectedEmail(int code) {
        return emailTicketsRepository.findById(code)//
                .orElseThrow(() -> new EmailNotFoundException(code));
    }

    public int getSolvedOrLockedEmails(int status, int userId) {
        return emailTicketCustomQueryRepository.getSolvedOrLockedEmails(status, userId);
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

    public List<Domain> whiteListDomain(int wbList) {
        return domainListRepository.findNameByWhiteBlackList(wbList);
    }

    public List<String> getKeywords() {
        List<String> keywords = new ArrayList<>();
        try {
            for (Keyword keyword : keywordRepository.findAll()) {
                keywords.add(keyword.getKeywordName());
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return keywords;
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

    public int getOfUnLockedEmails() {
        return emailTicketCustomQueryRepository.getOfUnLockedEmails();
    }

    public int getOfUnSolvedEmails() {
        return emailTicketCustomQueryRepository.getOfUnSolvedEmails();

    }

    public boolean insertUser(Users users) {
        try {
            userRepository.save(users);
        } catch (Exception e) {
            e.getLocalizedMessage();
            return false;
        }
        return true;
    }

    public boolean deleteRightsChartByUserCode(int userCode) {
        try {
            rightsChartCustomQueryRepository.deleteRights(userCode);
        } catch (Exception e) {

            e.getLocalizedMessage();
            return false;
        }
        return true;
    }

    public void insertRightsChart(RightChart rightChart) {
        rightsRepository.save(rightChart);
    }

    public List<Users> getCountEmailStatus(String filter) {
        return userCustomQueryRepository.getCountEmailStatus(filter);
    }
    public List<Users> averageCalculate() {
        return userCustomQueryRepository.averageCalculate();
    }
    public List<EmailTickets> getTicketsSolvedByUserDetails(int userCode, String filter) {
       return emailTicketCustomQueryRepository.ticketsSolvedByUserDetails(userCode, filter);
    }
    public  List<EmailTickets> clientReportWithDomain(int clientId,String filter){
        return emailTicketCustomQueryRepository.clientReportWithDomain(clientId,filter);
    }

    public List<Client> emailsPerClient(String filter) {
        return clientCustomQueryRepository.emailsPerClient(filter);
    }

    public void insertEmailList(List<EmailList> combinedList3) {
        for (EmailList email : combinedList3) {
            try {
                emailListRepository.save(email);
            } catch (DataIntegrityViolationException e) {
                e.getLocalizedMessage();
            }

        }
    }

    public boolean insertContact(Contact contact) {
        try {
            contactRepository.save(contact);
        } catch (Exception e) {
            e.getLocalizedMessage();
            return false;
        }
        return true;
    }

    public void deleteEmailListSingleRow(int code) {
        emailListCustomQuery.deleteEmailListRow(code);
    }

    public void deletePhoneListSingleRow(int code) {
        phoneListCustomQuery.deletePhoneListRow(code);
    }
}
