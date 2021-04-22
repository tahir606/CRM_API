package com.example.CRM;

import com.example.CRM.Client.ClientController;
import com.example.CRM.Email.EmailGeneral.EmailGeneralController;
import com.example.CRM.Email.EmailSent.EmailSentController;
import com.example.CRM.Email.EmailTicket.EmailTicketController;
import com.example.CRM.Email.EmailTicket.EmailTicketsRepository;
import com.example.CRM.Email.History.HistoryController;
import com.example.CRM.JCode.EmailDBHandler;
import com.example.CRM.Note.NoteController;
import com.example.CRM.User.UserController;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;


// mine
@Configuration

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Bean
    CommandLineRunner initDatabase(EmailDBHandler emailDBHandler, ClientController clientController, EmailSentController emailSentController, EmailTicketController emailTicketController
            , EmailGeneralController emailGeneralController, HistoryController historyController, UserController userController, EmailTicketsRepository emailTicketsRepository, NoteController noteController) {
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
//            log.debug("Solve Responder:"+ emailTicketController.solveEmail(530,22,0));
//            log.debug("read All Email General :"+emailDBHandler.readAllEmailsGeneral());
//            log.debug("sorry:"+emailTicketController.all());
//            log.debug("test:"+noteController.deleteUser(340,637));
//            log.debug("test:"+emailDBHandler.maxTicketNo());
//            log.debug("test:"+historyController.getHistory(1027));
//            log.debug("test:" + clientController.all());
        };
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    /**
     * Declare the JPA entity manager factory.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);

        // Classpath scanning of @Component, @Service, etc annotated class
        entityManagerFactory.setPackagesToScan(
                env.getProperty("entitymanager.packagesToScan"));

        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put(
                "hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        additionalProperties.put(
                "hibernate.show_sql",
                env.getProperty("hibernate.show_sql"));
        additionalProperties.put(
                "hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        entityManagerFactory.setJpaProperties(additionalProperties);

        return entityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // Private fields

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    // for encoding
    @Bean
    public HttpFirewall allowUrlEncodedPercentHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPercent(true);
        return firewall;
    }

////    for jkson
//
//    @Bean
//    public HttpMessageConverters customConverters() {
//
//        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//
//        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//        messageConverters.add(gsonHttpMessageConverter);
//
//        return new HttpMessageConverters(true, messageConverters);
//    }
}
