package com.example.CRM.Phone;

import com.example.CRM.User.*;
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
@RequestMapping("/phone_list")
public class PhoneController {
    private final PhoneListRepository phoneListRepository;
    private final PhoneListModelAssembler phoneListModelAssembler;


    public PhoneController(PhoneListRepository phoneListRepository, PhoneListModelAssembler phoneListModelAssembler) {
        this.phoneListRepository = phoneListRepository;
        this.phoneListModelAssembler = phoneListModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<PhoneList> one(@PathVariable int id) {

        PhoneList phoneList = phoneListRepository.findById(id)//
                .orElseThrow(() -> new phoneListNotFoundException(id));

        return phoneListModelAssembler.toModel(phoneList);
    }
    @GetMapping
    CollectionModel<EntityModel<PhoneList>> all() {

        List<EntityModel<PhoneList>> phoneList = phoneListRepository.findAll().stream() //
                .map(phoneListModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(phoneList, linkTo(methodOn(PhoneController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addPhoneNum(@RequestBody PhoneList phoneList) {

        EntityModel<PhoneList> entityModel = phoneListModelAssembler.toModel(phoneListRepository.save(phoneList));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePhone(@RequestBody PhoneList phoneList , @PathVariable int id){
        PhoneList phoneUpdate = phoneListRepository.findById(id)//
            .map(phone -> {
                phone.setClientID(phoneList.getClientID());
                phone.setContactID(phoneList.getContactID());
                phone.setLeadsId(phoneList.getLeadsId());
                phone.setNumber(phoneList.getNumber());
                phone.setUserCode(phoneList.getUserCode());
                return phoneListRepository.save(phone);
            })//
            .orElseGet(()->{
                phoneList.setPhoneID(id);
                return phoneListRepository.save(phoneList);
            });
        EntityModel<PhoneList> phoneListEntityModel = phoneListModelAssembler.toModel(phoneUpdate);
        return ResponseEntity //
                .created(phoneListEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(phoneListEntityModel);
    }

}
