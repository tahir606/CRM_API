package com.example.CRM.Event;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {
    @Override
    public EntityModel<Event> toModel(Event entity) {
        return EntityModel.of(entity,//
                WebMvcLinkBuilder.linkTo(methodOn(EventController.class).one(entity.getEventID())).withSelfRel(),
                linkTo(methodOn(EventController.class).all()).withRel("event_store"));
    }
}
