package com.example.CRM.Email;

import com.example.CRM.Email.EmailTicket.EmailTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface EmailRepository<T extends Email> extends JpaRepository<T,Integer> {

}
