package com.example.CRM.Client.ClientType;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientType")
public class ClientTypeController {

    private final ClientTypeRepository clientTypeRepository;
    private final ClientTypeModelAssembler clientTypeModelAssembler;

    public ClientTypeController(ClientTypeRepository clientTypeRepository, ClientTypeModelAssembler clientTypeModelAssembler) {
        this.clientTypeRepository = clientTypeRepository;
        this.clientTypeModelAssembler = clientTypeModelAssembler;
    }
    @GetMapping("/{id}")
    EntityModel<ClientType> one(@PathVariable int id){
        ClientType clientType = clientTypeRepository.findById(id) //
        .orElseThrow(()-> new ClientTypeNotFoundException(id));
        return clientTypeModelAssembler.toModel(clientType);
    }
    @GetMapping
    CollectionModel<EntityModel<ClientType>> all() {
        List<EntityModel<ClientType>> entityModels = clientTypeRepository.findAll().stream() //
                .map(clientTypeModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(entityModels ,linkTo(methodOn(ClientTypeController.class).all()).withSelfRel());
    }
    @RequestMapping("/getClientType")
    CollectionModel<EntityModel<ClientType>> getClientType() {
        List<EntityModel<ClientType>> entityModels = clientTypeRepository.findAll().stream() //
                .map(clientTypeModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(entityModels ,linkTo(methodOn(ClientTypeController.class).all()).withSelfRel());
    }
    @PostMapping
    ResponseEntity<?> newClientType(@RequestBody ClientType clientType){
        EntityModel<ClientType>  clientTypeEntityModel =clientTypeModelAssembler.toModel(clientTypeRepository.save(clientType));
        return ResponseEntity //
            .created(clientTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(clientTypeEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateClientType(@RequestBody ClientType clientType ,@PathVariable int id){
        ClientType updateClientType = clientTypeRepository.findById(id)//
            .map(type -> {
                type.setName(clientType.getName());
                return clientTypeRepository.save(type);
            }) //
            .orElseGet(()->{
               clientType.setCtCode(id);
               return clientTypeRepository.save(clientType);
            });

        EntityModel<ClientType> entityModel = clientTypeModelAssembler.toModel(updateClientType);
        return  ResponseEntity//
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }
}
