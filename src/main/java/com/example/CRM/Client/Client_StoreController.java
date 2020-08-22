package com.example.CRM.Client;


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
public class Client_StoreController {
    private final Client_StoreRepository client_storeRepository;
    private final Client_StoreModelAssembler client_storeModelAssembler;

    public Client_StoreController(Client_StoreRepository client_storeRepository, Client_StoreModelAssembler client_storeModelAssembler) {
        this.client_storeRepository = client_storeRepository;
        this.client_storeModelAssembler = client_storeModelAssembler;
    }


    @GetMapping("/{id}")
    EntityModel<Client_Store> one(@PathVariable int id){
        Client_Store client_store = client_storeRepository.findById(id)//
                .orElseThrow(()-> new Client_StoreNotFoundException(id));

        return client_storeModelAssembler.toModel(client_store);
    }
    @GetMapping
    CollectionModel<EntityModel<Client_Store>> all() {

        List<EntityModel<Client_Store>> client_store = client_storeRepository.findAll().stream() //
                .map(client_storeModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(client_store, linkTo(methodOn(Client_StoreController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newClient(@RequestBody Client_Store client_store) {

        EntityModel<Client_Store> client_storeEntityModel = client_storeModelAssembler.toModel(client_storeRepository.save(client_store));

        return ResponseEntity //
                .created(client_storeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(client_storeEntityModel);
    }
}
