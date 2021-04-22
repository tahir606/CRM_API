package com.example.CRM.Email.EmailSent;

import com.example.CRM.Email.Email;
import com.example.CRM.Email.EmailTicket.EmailSystem;
import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
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
    private final EmailDBHandler emailDBHandler;



    public EmailSentController(EmailSentRepository email_sentRepository, EmailSentModelAssembler email_sentModelAssembler, EmailSystem emailSystem, EmailDBHandler emailDBHandler) {
        this.email_sentRepository = email_sentRepository;
        this.email_sentModelAssembler = email_sentModelAssembler;
        this.emailSystem = emailSystem;
        this.emailDBHandler = emailDBHandler;
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

    @RequestMapping("/view/{id}")
    CollectionModel<EntityModel<EmailSent>> getOutBoxEmails(@PathVariable int id) {

        List<EntityModel<EmailSent>> email = email_sentRepository.findBySentAndFreezeOrderByCodeDesc(id,0).stream() //
                .map(email_sentModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailSentController.class).all()).withSelfRel());
    }

    @RequestMapping("/send/{userCode}")
    public boolean sendEmail(@RequestPart("email") Email email, @RequestParam("files") MultipartFile[] files, @PathVariable int userCode) throws AddressException, MessagingException, IOException {
        String address = emailSystem.getSingleAddress(email.getToAddress());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        email.setTimestamp(timestamp);
        Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return emailSystem.uploadFile(file, address);
                    } catch (IOException | MessagingException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());
        EmailSent emailSent =(EmailSent) email;
        emailSent.setUserCode(userCode);
        emailSent.setAttachment(emailSystem.setAttachmentPath(files, address));
        emailSystem.sendEmail(emailSent);
        return true;
    }


    @RequestMapping("/sendAgain/{code}")
    public boolean sendAgainFromOutbox(@PathVariable int code) throws IOException, MessagingException {
        EmailSent emailSent = email_sentRepository.findBySentAndCode(0, code);
        emailSystem.sendEmail(emailSent);
        return true;
    }

    @RequestMapping("/archive/{code}")
    public boolean moveToArchive(@PathVariable int code) {
        EmailSent emailSent =emailDBHandler.findSelectedSentEmail(code);
        emailSent.setFreeze(1);
        emailDBHandler.insertEmail(emailSent);
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
