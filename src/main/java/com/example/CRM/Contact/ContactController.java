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
public class ContactController {
    private final ContactRepository contact_Repository;
    private final ContactModelAssembler contact_ModelAssembler;

    public ContactController(ContactRepository contact_Repository, ContactModelAssembler contact_ModelAssembler) {
        this.contact_Repository = contact_Repository;
        this.contact_ModelAssembler = contact_ModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Contact> one(@PathVariable int id){
        Contact contact_ = contact_Repository.findById(id)//
                .orElseThrow(()-> new ContactNotFoundException(id));

        return contact_ModelAssembler.toModel(contact_);
    }
    @GetMapping
    CollectionModel<EntityModel<Contact>> all() {

        List<EntityModel<Contact>> contact_store = contact_Repository.findAll().stream() //
                .map(contact_ModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(contact_store, linkTo(methodOn(ContactController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newContact(@RequestBody Contact contact_) {

        EntityModel<Contact> contact_storeEntityModel = contact_ModelAssembler.toModel(contact_Repository.save(contact_));

        return ResponseEntity //
                .created(contact_storeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(contact_storeEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateContact(@RequestBody Contact contact ,@PathVariable int id){

        Contact updateContacts =contact_Repository.findById(id)//
            .map(contacts -> {
                contacts.setFirstName(contact.getFirstName());
                contacts.setLastName(contact.getLastName());
                contacts.setDateOfBirth(contact.getDateOfBirth());
                contacts.setAddress(contact.getAddress());
                contacts.setCity(contact.getCity());
                contacts.setCountry(contact.getCountry());
                contacts.setNote(contact.getNote());
                contacts.setCreatedOn(contact.getCreatedOn());
                contacts.setFreeze(contact.getFreeze());
                contacts.setClientID(contact.getClientID());
                contacts.setCreatedBy(contact.getCreatedBy());
                return contact_Repository.save(contacts);

            })//
            .orElseGet(()->{
               contact.setContactID(id);
               return  contact_Repository.save(contact);
            });
        EntityModel<Contact> entityModel = contact_ModelAssembler.toModel(updateContacts);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

}
