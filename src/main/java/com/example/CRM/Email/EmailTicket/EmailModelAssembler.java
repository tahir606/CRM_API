package com.example.CRM.Email.EmailTicket;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmailModelAssembler implements RepresentationModelAssembler<EmailTickets, EntityModel<EmailTickets>> {

    @Override
    public EntityModel<EmailTickets> toModel(EmailTickets emailTickets) {
        return EntityModel.of(emailTickets, //
                WebMvcLinkBuilder.linkTo(methodOn(EmailTicketController.class).one(emailTickets.getCode())).withSelfRel());
    }
}
