package com.example.CRM.Email.EmailList;

import com.example.CRM.JCode.EmailDBHandler;

import com.example.CRM.Product.ProductModule;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/emailList")
public class EmailListController {
    private final EmailListRepository emailListRepository;
    private final EmailListModelAssembler emailListModelAssembler;
    private final EmailDBHandler emailDBHandler;


    public EmailListController(EmailListRepository emailListRepository, EmailListModelAssembler emailListModelAssembler, EmailDBHandler emailDBHandler) {
        this.emailListRepository = emailListRepository;
        this.emailListModelAssembler = emailListModelAssembler;
        this.emailDBHandler = emailDBHandler;
    }

    @GetMapping("/{id}")
    EntityModel<EmailList> one(@PathVariable int id) {
        EmailList emailList = emailListRepository.findById(id)//
                .orElseThrow(() -> new EmailListNotFoundException(id));
        return emailListModelAssembler.toModel(emailList);

    }

    @GetMapping
    CollectionModel<EntityModel<EmailList>> all() {
        List<EntityModel<EmailList>> entityModels = emailListRepository.findAll().stream() //
                .map(emailListModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(entityModels, linkTo(methodOn(EmailListController.class).all()).withSelfRel());
    }

    @RequestMapping("/getEmailAddressList")
    public CollectionModel<EntityModel<EmailList>> getEmailAddressList() {
        List<EntityModel<EmailList>> entityModels = emailListRepository.findAll().stream() //
                .map(emailListModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(entityModels, linkTo(methodOn(EmailListController.class).all()).withSelfRel());
    }

    @RequestMapping("/addToEmailList")
    public void addToEmailList(@RequestBody List<EmailList> emailLists) {
        emailDBHandler.insertEmailList(emailLists);

    }

    @RequestMapping("/updateEmailList")
    ResponseEntity<?> updateEmailList(@RequestBody EmailList emailList) {

        int isUpdate= emailDBHandler.updateEmailList(emailList);
        if (isUpdate==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(emailList.getEmailID(),HttpStatus.OK);
    }
    @RequestMapping("/addEmail")
    public ResponseEntity<?> addEmail(@RequestBody EmailList emailList) {
        EntityModel<EmailList> emailListEntityModel = null;

        ResponseEntity<?> responseEntity = null;
        try {
            emailListEntityModel = emailListModelAssembler.toModel(emailListRepository.save(emailList));
            responseEntity = ResponseEntity //
                    .created(emailListEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(emailListEntityModel);
        } catch (DataIntegrityViolationException e) {
            e.getLocalizedMessage();
        }

        return responseEntity;

    }

    @RequestMapping("/deleteEmailList/{code}")
    ResponseEntity<?> delete(@PathVariable int code) {

        emailDBHandler.deleteEmailListSingleRow(code);
//        emailListRepository.deleteById(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity<?> emailList(@RequestBody EmailList emailList) {
        EntityModel<EmailList> emailListEntityModel = emailListModelAssembler.toModel(emailListRepository.save(emailList));

        return ResponseEntity //
                .created(emailListEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(emailListEntityModel);
    }
}
