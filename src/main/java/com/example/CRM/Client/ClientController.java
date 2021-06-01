package com.example.CRM.Client;


import com.example.CRM.JCode.EmailDBHandler;
import com.example.CRM.User.UserSystem;
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
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository client_Repository;
    private final ClientModelAssembler client_ModelAssembler;
    private final EmailDBHandler emailDBHandler;
    private final ClientSystem clientSystem;

    public ClientController(ClientRepository client_Repository, ClientModelAssembler client_ModelAssembler, EmailDBHandler emailDBHandler, ClientSystem clientSystem) {
        this.client_Repository = client_Repository;
        this.client_ModelAssembler = client_ModelAssembler;
        this.emailDBHandler = emailDBHandler;
        this.clientSystem = clientSystem;
    }


    @GetMapping("/{id}")
    EntityModel<Client> one(@PathVariable int id) {
        Client client_ = client_Repository.findById(id)//
                .orElseThrow(() -> new ClientNotFoundException(id));

        return client_ModelAssembler.toModel(client_);
    }

    @GetMapping
    public CollectionModel<EntityModel<Client>> all() {

        List<EntityModel<Client>> client_store = client_Repository.findAll().stream() //
                .map(client_ModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(client_store, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @RequestMapping("/{id}")
    EntityModel<Client> getClient(@PathVariable int id) {
        Client client_ = client_Repository.findById(id)//
                .orElseThrow(() -> new ClientNotFoundException(id));

        return client_ModelAssembler.toModel(client_);
    }

    @RequestMapping("/clientList")
    public CollectionModel<EntityModel<Client>> getClientList() {

        List<EntityModel<Client>> client_store = client_Repository.findAll().stream() //
                .map(client_ModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(client_store, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @RequestMapping("/emailsPerClient")
    public CollectionModel<EntityModel<Client>> emailsPerClient(@RequestParam String from,@RequestParam String to) {
        String filterTicket = clientSystem.filterTime(from,to);
        List<EntityModel<Client>> client_store = emailDBHandler.emailsPerClient(filterTicket).stream() //
                .map(client_ModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(client_store, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @RequestMapping("/addClient")
    ResponseEntity<?> addClient(@RequestBody Client client) {

        EntityModel<Client> entityModel = client_ModelAssembler.toModel(client_Repository.save(client));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
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
                .orElseGet(() -> {
                    client.setClientID(id);
                    return client_Repository.save(client);
                });
        EntityModel<Client> entityModel = client_ModelAssembler.toModel(updateClient);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


}
