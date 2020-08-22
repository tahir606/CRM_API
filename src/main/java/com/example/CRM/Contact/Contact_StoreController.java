package com.example.CRM.Contact;


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
@RequestMapping("/contact_store")
public class Contact_StoreController {
    private final Contact_StoreRepository contact_storeRepository;
    private final Contact_StoreModelAssembler contact_storeModelAssembler;

    public Contact_StoreController(Contact_StoreRepository contact_storeRepository, Contact_StoreModelAssembler contact_storeModelAssembler) {
        this.contact_storeRepository = contact_storeRepository;
        this.contact_storeModelAssembler = contact_storeModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Contact_Store> one(@PathVariable int id){
        Contact_Store contact_store = contact_storeRepository.findById(id)//
                .orElseThrow(()-> new Contact_StoreNotFoundException(id));

        return contact_storeModelAssembler.toModel(contact_store);
    }
    @GetMapping
    CollectionModel<EntityModel<Contact_Store>> all() {

        List<EntityModel<Contact_Store>> contact_store = contact_storeRepository.findAll().stream() //
                .map(contact_storeModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(contact_store, linkTo(methodOn(Contact_StoreController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newContact(@RequestBody Contact_Store contact_store) {

        EntityModel<Contact_Store> contact_storeEntityModel = contact_storeModelAssembler.toModel(contact_storeRepository.save(contact_store));

        return ResponseEntity //
                .created(contact_storeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(contact_storeEntityModel);
    }

}
