package com.example.CRM.Email.EmailGeneral;

import com.example.CRM.Email.EmailRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailGeneralRepository extends EmailRepository<EmailGeneral> {

    List<EmailGeneral> findAllByFreezeOrderByCodeDesc(int freeze);

}
