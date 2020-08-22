package com.example.CRM.Email.EmailSent;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Email_SentModelAssembler implements RepresentationModelAssembler<Email_Sent, EntityModel<Email_Sent>> {
    @Override
    public EntityModel<Email_Sent> toModel(Email_Sent entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(Email_SentController.class).one( entity.getEmailNo())).withSelfRel(),
                linkTo(methodOn(Email_SentController.class).all()).withRel("email_sent"));
    }
}
