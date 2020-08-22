package com.example.CRM.Email.EmailSent;

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
@RequestMapping("/email_sent")
public class Email_SentController {
    private final Email_SentRepository email_sentRepository;
    private final Email_SentModelAssembler email_sentModelAssembler;

    public Email_SentController(Email_SentRepository email_sentRepository, Email_SentModelAssembler email_sentModelAssembler) {
        this.email_sentRepository = email_sentRepository;
        this.email_sentModelAssembler = email_sentModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Email_Sent> one(@PathVariable int id){
        Email_Sent email_sent = email_sentRepository.findById(id)//
                .orElseThrow(()-> new Email_SentNotFoundException(id));

        return email_sentModelAssembler.toModel(email_sent);
    }
    @GetMapping
    CollectionModel<EntityModel<Email_Sent>> all() {

        List<EntityModel<Email_Sent>> email = email_sentRepository.findAll().stream() //
                .map(email_sentModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(email, linkTo(methodOn(Email_SentController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newEmailSent(@RequestBody Email_Sent email) {

        EntityModel<Email_Sent> entityModel = email_sentModelAssembler.toModel(email_sentRepository.save(email));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


}
