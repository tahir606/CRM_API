package com.example.CRM.Contact;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContactModelAssembler implements RepresentationModelAssembler<Contact, EntityModel<Contact>> {
    @Override
    public EntityModel<Contact> toModel(Contact contact_) {
        return EntityModel.of(contact_, //
                WebMvcLinkBuilder.linkTo(methodOn(ContactController.class).one( contact_.getContactID())).withSelfRel(),
                linkTo(methodOn(ContactController.class).all()).withRel("contact_store"));
    }
}
