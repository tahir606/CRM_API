package com.example.CRM.Client;


import com.example.CRM.User.Users;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/client_store")
public class ClientController {
    private final ClientRepository client_Repository;
    private final ClientModelAssembler client_ModelAssembler;

    public ClientController(ClientRepository client_Repository, ClientModelAssembler client_ModelAssembler) {
        this.client_Repository = client_Repository;
        this.client_ModelAssembler = client_ModelAssembler;
    }


    @GetMapping("/{id}")
    EntityModel<Client> one(@PathVariable int id) {
        Client client_ = client_Repository.findById(id)//
                .orElseThrow(() -> new ClientNotFoundException(id));

        return client_ModelAssembler.toModel(client_);
    }

    @GetMapping
    CollectionModel<EntityModel<Client>> all() {

        List<EntityModel<Client>> client_store = client_Repository.findAll().stream() //
                .map(client_ModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(client_store, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newClient(@RequestBody Client client_) {

        EntityModel<Client> client_storeEntityModel = client_ModelAssembler.toModel(client_Repository.save(client_));

        return ResponseEntity //
                .created(client_storeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(client_storeEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateClient(@RequestBody Client client, @PathVariable int id) {
        Client updateClient = client_Repository.findById(id) //
                .map(client1 -> {
                    client1.setName(client.getName());
                    client1.setOwner(client.getOwner());
                    client1.setEmail(client.getEmail());
                    client1.setPhoneNo(client.getPhoneNo());
                    client1.setAddress(client.getAddress());
                    client1.setCity(client.getCity());
                    client1.setCountry(client.getCountry());
                    client1.setWebsite(client.getWebsite());
                    client1.setJoinDate(client.getJoinDate());
                    client1.setBicycle(client.getBicycle());
                    client1.setType(client.getType());
                    client1.setFromLead(client.getFromLead());
                    client1.setCreatedBy(client.getCreatedBy());
                    client1.setCreatedOn(client.getCreatedOn());
                    return client_Repository.save(client1);
                }) //
                .orElseGet(()->{
                   client.setClientID(id);
                   return client_Repository.save(client);
                });
        EntityModel<Client> entityModel = client_ModelAssembler.toModel(updateClient);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


}
