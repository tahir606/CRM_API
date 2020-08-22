package com.example.CRM.Contact;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Contact_StoreModelAssembler implements RepresentationModelAssembler<Contact_Store, EntityModel<Contact_Store>> {
    @Override
    public EntityModel<Contact_Store> toModel(Contact_Store contact_store) {
        return EntityModel.of(contact_store, //
                WebMvcLinkBuilder.linkTo(methodOn(Contact_StoreController.class).one( contact_store.getContact_ID())).withSelfRel(),
                linkTo(methodOn(Contact_StoreController.class).all()).withRel("contact_store"));
    }
}
