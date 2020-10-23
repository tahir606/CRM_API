package com.example.CRM.Email.Setiings;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SettingModelAssembler implements RepresentationModelAssembler<EmailSettings, EntityModel<EmailSettings>> {

    @Override
    public EntityModel<EmailSettings> toModel(EmailSettings entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(SettingsController.class).one(entity.getCode())).withSelfRel(),
                linkTo(methodOn(SettingsController.class).all()).withRel("email_settings"));
    }
}
