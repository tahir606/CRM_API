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
//   EmailTickets findByCodeAndFreeze(int code ,int freeze); // this for code

//    @Query("SELECT a FROM email_ticket a WHERE 1=:s")
//    List<EmailTickets> findAll(String s);
//    int countBySolvedAndSolvedBy(char solve, int solveBy);
//
//    int countByLockedAndSolvedBy(int locked, int solveBy);
//
//    int findTopByOrderByCodeDesc();
//
//    @Query("SELECT a FROM  email_ticket a WHERE  a.solved =  :s  ")
//    List<EmailTickets> findBySolved(char s);
//
//    @Query("SELECT a FROM email_ticket a WHERE  a.subject NOT LIKE ?1")
//    List<EmailTickets> findBySubject(String s);


//    @Query("SELECT a FROM email_ticket a WHERE a.locked != :locked ")
//    List<EmailTickets> findALlByLocked(int locked);
//
//    List<EmailTickets> findByLocked(int locked);
//
//    List<EmailTickets> findByFreeze(int freeze);

}