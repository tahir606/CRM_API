package com.example.CRM;

import com.example.CRM.Email.EmailStore.EmailSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    CommandLineRunner initDatabase(EmailSystem emailSystem) {
        return args -> {
//            log.debug("Receiving email: " + emailSystem.receiveEmail());

        };
    }

}
