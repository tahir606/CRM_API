package com.example.CRM.Phone;

import com.example.CRM.Email.EmailList.EmailList;
import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/phoneList")
public class PhoneController {
    private final PhoneListRepository phoneListRepository;
    private final PhoneListModelAssembler phoneListModelAssembler;
    private final EmailDBHandler emailDBHandler;


    public PhoneController(PhoneListRepository phoneListRepository, PhoneListModelAssembler phoneListModelAssembler, EmailDBHandler emailDBHandler) {
        this.phoneListRepository = phoneListRepository;
        this.phoneListModelAssembler = phoneListModelAssembler;
        this.emailDBHandler = emailDBHandler;
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
    @RequestMapping("/addPhone")
    public ResponseEntity<?> addEmail(@RequestBody PhoneList phoneList) {
        EntityModel<PhoneList> entityModel = phoneListModelAssembler.toModel(phoneListRepository.save(phoneList));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);

    }
    @RequestMapping("/updatePhoneList")
    ResponseEntity<?> updatePhoneList(@RequestBody PhoneList phoneList) {

        int isUpdate= emailDBHandler.updatePhoneList(phoneList);
        if (isUpdate==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(phoneList.getPhoneID(),HttpStatus.OK);
    }
    @RequestMapping("/deletePhoneList/{code}")
    ResponseEntity<?> delete(@PathVariable int code) {
        emailDBHandler.deletePhoneListSingleRow(code);
        return ResponseEntity.noContent().build();
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
