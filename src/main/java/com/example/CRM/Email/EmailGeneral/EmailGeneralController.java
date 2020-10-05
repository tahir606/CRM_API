package com.example.CRM.Email.EmailGeneral;

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
@RequestMapping("/email_general")
public class EmailGeneralController {
    private final EmailGeneralRepository email_generalRepository;
    private final EmailGeneralModelAssembler email_generalModelAssembler;

    public EmailGeneralController(EmailGeneralRepository email_generalRepository, EmailGeneralModelAssembler email_generalModelAssembler) {
        this.email_generalRepository = email_generalRepository;
        this.email_generalModelAssembler = email_generalModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<EmailGeneral> one(@PathVariable int id){
        EmailGeneral email_general =email_generalRepository.findById(id)//
        .orElseThrow(()-> new EmailGeneralNotFoundException(id));
        return email_generalModelAssembler.toModel(email_general);
    }

    @GetMapping
    CollectionModel<EntityModel<EmailGeneral>> all(){
        List<EntityModel<EmailGeneral>> email_general =email_generalRepository.findAll().stream() //
                .map(email_generalModelAssembler::toModel)//
                .collect(Collectors.toList());
        return CollectionModel.of(email_general, linkTo(methodOn(EmailGeneralController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newEmailGeneral(@RequestBody EmailGeneral email_general){
        EntityModel<EmailGeneral> entityModel = email_generalModelAssembler.toModel(email_generalRepository.save(email_general));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

}
