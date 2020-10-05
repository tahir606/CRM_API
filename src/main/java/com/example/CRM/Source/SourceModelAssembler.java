package com.example.CRM.Source;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SourceModelAssembler implements RepresentationModelAssembler<Source, EntityModel<Source>> {
    @Override
    public EntityModel<Source> toModel(Source entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(SourceController.class).one( entity.getSourceID())).withSelfRel(),
                linkTo(methodOn(SourceController.class).all()).withRel("source_list"));
    }
}
