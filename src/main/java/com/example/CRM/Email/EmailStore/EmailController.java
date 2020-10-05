package com.example.CRM.Email.EmailStore;

import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/Email")
@Component
public class EmailController {

    private final EmailRepository emailRepository;
    private final EmailModelAssembler emailModelAssembler;

    public EmailController(EmailRepository emailRepository, EmailModelAssembler emailModelAssembler) {
        this.emailRepository = emailRepository;
        this.emailModelAssembler = emailModelAssembler;
    }


    @GetMapping("/{id}")
    public EntityModel<EmailTickets> one(@PathVariable int id) {
        EmailTickets emailTickets = emailRepository.findById(id)//
                .orElseThrow(() -> new EmailNotFoundException(id));

        return emailModelAssembler.toModel(emailTickets);
    }

    @GetMapping
    public CollectionModel<EntityModel<EmailTickets>> all() {

        List<EntityModel<EmailTickets>> email = emailRepository.findAll().stream() //
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> insertEmail(@RequestBody EmailTickets emailTickets) {
        EmailDBHandler emailDBHandler = new EmailDBHandler(emailRepository);
        EntityModel<EmailTickets> entityModel = emailModelAssembler.toModel(emailDBHandler.emailDataInsert(emailTickets));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestMapping("/send")
    public String sendEmail(@RequestBody EmailTickets emailTickets) throws AddressException, MessagingException, IOException {
        EmailSystem emailSystem = new EmailSystem(emailRepository);
        emailSystem.sendmail(emailTickets);
        return "Email Send Successfully";
    }

    @PutMapping
    ResponseEntity<?> updateEmails(@RequestBody EmailTickets emailTickets, @PathVariable int id) {
        EmailTickets tickets = emailRepository.findById(id)//
                .map(emailTicket -> {
                    emailTicket.setMessageNo(emailTickets.getMessageNo());
                    emailTicket.setSubject(emailTickets.getSubject());
                    emailTicket.setToAddress(emailTickets.getToAddress());
                    emailTicket.setFromAddress(emailTickets.getFromAddress());
                    emailTicket.setCcAddress(emailTickets.getCcAddress());
                    emailTicket.setBody(emailTickets.getBody());
                    emailTicket.setAttachment(emailTickets.getAttachment());
                    emailTicket.setSolved(emailTickets.getSolved());
                    emailTicket.setSolvedBy(emailTickets.getSolvedBy());
                    emailTicket.setSolveTime(emailTickets.getSolveTime());
                    emailTicket.setLocked(emailTickets.getLocked());
                    emailTicket.setTimesTamp(emailTickets.getTimesTamp());
                    emailTicket.setManualEmail(emailTickets.getManualEmail());
                    emailTicket.setLockTime(emailTickets.getLockTime());
                    emailTicket.setFreeze(emailTickets.getFreeze());
                    return emailRepository.save(emailTicket);
                })//
                .orElseGet(() -> {
                    emailTickets.setCode(id);
                    return emailRepository.save(emailTickets);
                });
        EntityModel<EmailTickets> entityModel = emailModelAssembler.toModel(tickets);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

}
