package com.example.CRM.LeadStore;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LeadModelAssembler implements RepresentationModelAssembler<Lead, EntityModel<Lead>> {
    @Override
    public EntityModel<Lead> toModel(Lead entity) {
        return  EntityModel.of(entity ,//
                WebMvcLinkBuilder.linkTo(methodOn(LeadController.class).one(entity.getLeadsId())).withSelfRel(),
                linkTo(methodOn(LeadController.class).all()).withRel("lead_store"));
    }
}
