package com.example.CRM.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EmailRepository<T extends Email> extends JpaRepository<T,Integer> {

}
