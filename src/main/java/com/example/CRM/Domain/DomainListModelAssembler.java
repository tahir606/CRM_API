package com.example.CRM.Domain;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class DomainListModelAssembler implements RepresentationModelAssembler<Domain, EntityModel<Domain>> {
    @Override
    public EntityModel<Domain> toModel(Domain entity) {

        return EntityModel.of(entity ,//
                WebMvcLinkBuilder.linkTo(methodOn(DomainListController.class).one(entity.getDomainCode())).withSelfRel(),
                linkTo(methodOn(DomainListController.class).all()).withRel("domain_list"));

    }
}
