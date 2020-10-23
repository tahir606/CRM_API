package com.example.CRM.Email.EmailTicket;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailTicketsRepository extends EmailRepository<EmailTickets> {

    List<EmailTickets> findBySolved(char solve);

    List<EmailTickets> findByLocked(int locked);

    int countBySolvedAndSolvedBy(char solve, int solveBy);

    int countByLockedAndSolvedBy(int locked, int solveBy);

    int findTopByOrderByCodeDesc();


}
