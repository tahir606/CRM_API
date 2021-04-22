package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Email.EmailRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailTicketsRepository extends EmailRepository<EmailTickets> {
    @Query("SELECT a FROM email_ticket a WHERE a.status=:s")
    List<EmailTickets> findAll(String s);
    EmailTickets findByTicketNoAndFreeze(int code ,int freeze);
    EmailTickets findFirstByOrderByTicketNoDesc();
    EmailTickets findFirstByOrderByCodeDesc();
    List<EmailTickets> findByTicketNoGreaterThan(int ticketNo);
    EmailTickets findByCode(int code);
    int countByStatus(Status status);

}