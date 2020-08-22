package com.example.CRM.Email.EmailRelation;

import com.example.CRM.Email.EmailList.EmailListController;
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
@RequestMapping("/email_relation")
public class EmailRelationController {
    private final EmailRelationRepository emailRelationRepository;
    private final EmailRelationModelAssembler emailRelationModelAssembler;

    public EmailRelationController(EmailRelationRepository emailRelationRepository, EmailRelationModelAssembler emailRelationModelAssembler) {
        this.emailRelationRepository = emailRelationRepository;
        this.emailRelationModelAssembler = emailRelationModelAssembler;
    }
    @GetMapping("/{id}")
    EntityModel<EmailRelation> one(@PathVariable int id){
        EmailRelation emailRelation = emailRelationRepository.findById(id)//
                .orElseThrow(()-> new EmailRelationNotFoundException(id));
        return emailRelationModelAssembler.toModel(emailRelation);

    }

    @GetMapping
    CollectionModel<EntityModel<EmailRelation>> all(){
        List<EntityModel<EmailRelation>> entityModels =emailRelationRepository.findAll().stream() //
                .map(emailRelationModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(entityModels, linkTo(methodOn(EmailRelationController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> emailRelation(@RequestBody EmailRelation emailRelation){
        EntityModel<EmailRelation> emailRelationEntityModel =emailRelationModelAssembler.toModel(emailRelationRepository.save(emailRelation));

        return ResponseEntity //
                .created(emailRelationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(emailRelationEntityModel);
    }

}
