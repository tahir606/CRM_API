package com.example.CRM;

import com.example.CRM.attachmentProperty.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CRMApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRMApplication.class, args);

    }

}
