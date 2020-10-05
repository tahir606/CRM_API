package com.example.CRM.Email.EmailSent;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmailSentModelAssembler implements RepresentationModelAssembler<EmailSent, EntityModel<EmailSent>> {
    @Override
    public EntityModel<EmailSent> toModel(EmailSent entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(EmailSentController.class).one( entity.getEmailNo())).withSelfRel(),
                linkTo(methodOn(EmailSentController.class).all()).withRel("email_sent"));
    }
}
