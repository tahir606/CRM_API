package com.example.CRM.Email.EmailSent;

import com.example.CRM.Email.EmailRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmailSentRepository extends EmailRepository<EmailSent> {

    List<EmailSent> findBySentOrderByCodeDesc(int sent);
    List<EmailSent> findBySentAndFreezeOrderByCodeDesc(int sent,int freeze);
    EmailSent findBySentAndCode(int sent,int code);
}
