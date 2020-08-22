package com.example.CRM.Email.EmailStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailTickets,Integer> {
}
