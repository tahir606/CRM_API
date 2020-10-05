package com.example.CRM.Client.ClientType;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ClientTypeModelAssembler implements RepresentationModelAssembler<ClientType ,EntityModel<ClientType>> {

    @Override
    public EntityModel<ClientType> toModel(ClientType clientType) {
        return EntityModel.of(clientType, //
                WebMvcLinkBuilder.linkTo(methodOn(ClientTypeController.class).one(clientType.getCtCode())).withSelfRel(),
                linkTo(methodOn(ClientTypeController.class).all()).withRel("client_type"));

    }
}
