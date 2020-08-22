package com.example.CRM.Email.EmailGeneral;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmailGeneralModelAssembler implements RepresentationModelAssembler<EmailGeneral, EntityModel<EmailGeneral>> {
    @Override
    public EntityModel<EmailGeneral> toModel(EmailGeneral entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(EmailGeneralController.class).one( entity.getEmailNo())).withSelfRel(),
                linkTo(methodOn(EmailGeneralController.class).all()).withRel("email_general"));
    }
}
