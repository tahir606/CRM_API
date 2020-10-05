package com.example.CRM;

import com.example.CRM.Email.EmailStore.EmailRepository;
import com.example.CRM.Email.EmailStore.EmailSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CRMApplication {

	public static void main(String[] args) {
		SpringApplication.run(CRMApplication.class, args);

	}

}
