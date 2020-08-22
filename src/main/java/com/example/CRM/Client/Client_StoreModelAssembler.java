package com.example.CRM.Client;

import com.example.CRM.Client.Client_Store;
import com.example.CRM.Client.Client_StoreController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Client_StoreModelAssembler implements RepresentationModelAssembler<Client_Store, EntityModel<Client_Store>> {
    @Override
    public EntityModel<Client_Store> toModel(Client_Store client_store) {
        return EntityModel.of(client_store, //
                WebMvcLinkBuilder.linkTo(methodOn(Client_StoreController.class).one( client_store.getClient_ID())).withSelfRel(),
                linkTo(methodOn(Client_StoreController.class).all()).withRel("client_store"));
    }
}
