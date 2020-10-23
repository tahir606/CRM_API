package com.example.CRM;

import com.example.CRM.Email.EmailGeneral.EmailGeneralController;
import com.example.CRM.Email.EmailSent.EmailSentController;
import com.example.CRM.Email.EmailTicket.EmailTicketController;
import com.example.CRM.JCode.EmailDBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    CommandLineRunner initDatabase(EmailDBHandler emailDBHandler, EmailSentController emailSentController, EmailTicketController emailTicketController, EmailGeneralController emailGeneralController) {
        return args -> {
//            log.debug("Read email sent: " + emailDBHandler.readAllEmailsSent(1));
//            log.debug("Read email store: " + emailDBHandler.readAllEmailsStore());
//            log.debug("read emails solved:"+emailDBHandler.readAllSolvedEmails('y'));
//            log.debug("read emails locked:"+emailDBHandler.readAllLockedEmails());
//            log.debug("read no of solved Emails :"+emailDBHandler.getNoOfSolvedEmailsByUser('Y', 22));
//            log.debug("read no of Locked Emails :"+emailDBHandler.getNoOfLockedUnlockedEmailsByUser(1, 22));
////            log.debug("Latest Email No :"+emailDBHandler.getLatestEmailNo()); // error
//            log.debug("update as locked and LockedTime:"+emailDBHandler.lockEmail(3,1));
//            log.debug("domains:     "+emailDBHandler.whiteListDomain(1));
//            log.debug("sendAgainFromOutBox:"+emailSentController.sendAgainFromOutbox(0,183));
//            log.debug("sendAgainFromOutBox:"+emailGeneralController.createTicketFromGeneral(192));
//            log.debug("Solve Responder:"+ emailTicketController.solveEmail(189));
//            log.debug("read All Email General :"+emailDBHandler.readAllEmailsGeneral());
        };
    }

}
