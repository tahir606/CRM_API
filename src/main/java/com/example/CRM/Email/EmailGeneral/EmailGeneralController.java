package com.example.CRM.Email.EmailGeneral;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailTicket.EmailNotFoundException;
import com.example.CRM.Email.EmailTicket.EmailSystem;
import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/general")
public class EmailGeneralController {
    private final EmailGeneralRepository emailGeneralRepository;
    private final EmailGeneralModelAssembler email_generalModelAssembler;
    private final EmailSystem emailSystem;
    private final EmailDBHandler emailDBHandler;

    public EmailGeneralController(EmailGeneralRepository emailGeneralRepository, EmailGeneralModelAssembler email_generalModelAssembler, EmailSystem emailSystem, EmailDBHandler emailDBHandler) {
        this.emailGeneralRepository = emailGeneralRepository;
        this.email_generalModelAssembler = email_generalModelAssembler;
        this.emailSystem = emailSystem;
        this.emailDBHandler = emailDBHandler;
    }

    @GetMapping("/{id}")
    EntityModel<EmailGeneral> one(@PathVariable int id) {
        EmailGeneral email_general = emailGeneralRepository.findById(id)//
                .orElseThrow(() -> new EmailGeneralNotFoundException(id));
        return email_generalModelAssembler.toModel(email_general);
    }

    @GetMapping
    CollectionModel<EntityModel<EmailGeneral>> all() {
        List<EntityModel<EmailGeneral>> email_general = emailGeneralRepository.findAll().stream() //
                .map(email_generalModelAssembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(email_general, linkTo(methodOn(EmailGeneralController.class).all()).withSelfRel());
    }

    @RequestMapping("/ticket/{code}")
    public boolean createTicketFromGeneral(@PathVariable int code) throws IOException, MessagingException {
        Email email = emailGeneralRepository.findById(code)//
                .orElseThrow(() -> new EmailNotFoundException(code));
        EmailTickets emailTickets = convertToEmailTicket(email);
        emailDBHandler.insertEmail(emailTickets);
        if (emailSystem.autoReplyTicket(emailTickets)) {
            email.setFreeze(1);
            emailDBHandler.insertEmail(email);
        }
        return true;
    }

    public EmailTickets convertToEmailTicket(Email email) {
        EmailTickets emailTickets = new EmailTickets();
        emailTickets.setToAddress(email.getFromAddress());
        emailTickets.setFromAddress(email.getFromAddress());
        emailTickets.setCcAddress(email.getCcAddress());
        emailTickets.setTimestamp(email.getTimestamp());
        emailTickets.setAttachment(email.getAttachment());
        emailTickets.setSubject(email.getSubject());
        emailTickets.setBody(email.getBody());
        emailTickets.setManualEmail(1);
        return emailTickets;
    }

//    @PostMapping
//    ResponseEntity<?> newEmailGeneral(@RequestBody EmailGeneral email_general) {
//        EntityModel<EmailGeneral> entityModel = email_generalModelAssembler.toModel(emailGeneralRepository.save(email_general));
//        return ResponseEntity //
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//                .body(entityModel);
//    }

}
