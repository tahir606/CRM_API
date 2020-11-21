package com.example.CRM.Email.History;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class HistoryModelAssembler implements RepresentationModelAssembler<TicketHistory, EntityModel<TicketHistory>> {

    @Override
    public EntityModel<TicketHistory> toModel(TicketHistory historyMaintain) {
        return EntityModel.of(historyMaintain, //
                WebMvcLinkBuilder.linkTo(methodOn(HistoryController.class).one(historyMaintain.gethCode())).withSelfRel());
    }
}
