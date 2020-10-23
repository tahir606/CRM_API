package com.example.CRM.Email.EmailGeneral;

import com.example.CRM.Email.EmailRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailGeneralRepository extends EmailRepository<EmailGeneral> {
}
