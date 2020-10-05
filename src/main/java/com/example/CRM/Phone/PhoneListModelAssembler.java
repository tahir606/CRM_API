package com.example.CRM.Phone;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhoneListModelAssembler implements RepresentationModelAssembler<PhoneList, EntityModel<PhoneList>> {
    @Override
    public EntityModel<PhoneList> toModel(PhoneList entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(PhoneController.class).one( entity.getPhoneID())).withSelfRel(),
                linkTo(methodOn(PhoneController.class).all()).withRel("phone_list"));
    }
}
