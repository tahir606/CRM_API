package com.example.CRM.Email.EmailRelation;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmailRelationModelAssembler implements RepresentationModelAssembler<EmailRelation, EntityModel<EmailRelation>> {
    @Override
    public EntityModel<EmailRelation> toModel(EmailRelation entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(EmailRelationController.class).one(entity.getEmailNo())).withSelfRel(),
                linkTo(methodOn(EmailRelationController.class).all()).withRel("email_relation"));
    }
}
