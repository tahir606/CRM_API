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
@RequestMapping("/client_type")
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

    @PostMapping
    ResponseEntity<?> newClientType(@RequestBody ClientType clientType){
        EntityModel<ClientType>  clientTypeEntityModel =clientTypeModelAssembler.toModel(clientTypeRepository.save(clientType));
        return ResponseEntity //
            .created(clientTypeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(clientTypeEntityModel);
    }
}
