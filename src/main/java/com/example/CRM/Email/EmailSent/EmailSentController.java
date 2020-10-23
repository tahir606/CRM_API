package com.example.CRM.Email.EmailSent;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailTicket.EmailSystem;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/email")
public class EmailSentController {
    private final EmailSentRepository email_sentRepository;
    private final EmailSentModelAssembler email_sentModelAssembler;
    private final EmailSystem emailSystem;

    public EmailSentController(EmailSentRepository email_sentRepository, EmailSentModelAssembler email_sentModelAssembler, EmailSystem emailSystem) {
        this.email_sentRepository = email_sentRepository;
        this.email_sentModelAssembler = email_sentModelAssembler;
        this.emailSystem = emailSystem;
    }

    @GetMapping("/{id}")
    EntityModel<EmailSent> one(@PathVariable int id) {
        EmailSent email_sent = email_sentRepository.findById(id)//
                .orElseThrow(() -> new EmailSentNotFoundException(id));

        return email_sentModelAssembler.toModel(email_sent);
    }

    @GetMapping
    CollectionModel<EntityModel<EmailSent>> all() {

        List<EntityModel<EmailSent>> email = email_sentRepository.findAll().stream() //
                .map(email_sentModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailSentController.class).all()).withSelfRel());
    }

    @RequestMapping("/send")
    public boolean sendEmail(@RequestBody Email email) throws AddressException, MessagingException, IOException {
        emailSystem.sendEmail(email);
        return true;
    }

    @RequestMapping("/sendAgain/{code}/{sent}")
    public boolean sendAgainFromOutbox(@PathVariable int code, @PathVariable int sent) throws IOException, MessagingException {
        EmailSent emailSent = email_sentRepository.findBySentAndCode(sent, code);
        emailSystem.sendEmail(emailSent);
        return true;
    }


    @PostMapping
    ResponseEntity<?> newEmailSent(@RequestBody EmailSent email) {

        EntityModel<EmailSent> entityModel = email_sentModelAssembler.toModel(email_sentRepository.save(email));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


}
