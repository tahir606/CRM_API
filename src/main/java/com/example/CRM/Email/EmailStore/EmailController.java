package com.example.CRM.Email.EmailStore;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/email_store")
public class EmailController {
    private final EmailRepository emailRepository;
    private final EmailModelAssembler emailModelAssembler;

    public EmailController(EmailRepository emailRepository, EmailModelAssembler emailModelAssembler) {
        this.emailRepository = emailRepository;
        this.emailModelAssembler = emailModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<EmailTickets> one(@PathVariable int id){
        EmailTickets emailTickets = emailRepository.findById(id)//
        .orElseThrow(()-> new EmailNotFoundException(id));

        return emailModelAssembler.toModel(emailTickets);
    }
    @GetMapping
    CollectionModel<EntityModel<EmailTickets>> all() {

        List<EntityModel<EmailTickets>> email = emailRepository.findAll().stream() //
                .map(emailModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(EmailController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newEmail(@RequestBody EmailTickets emailTickets) {

        EntityModel<EmailTickets> entityModel = emailModelAssembler.toModel(emailRepository.save(emailTickets));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    }
