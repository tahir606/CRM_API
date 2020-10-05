package com.example.CRM.Domain;

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
@RequestMapping("domain_list")
public class DomainListController {
    private final DomainListRepository domainListRepository;
    private final DomainListModelAssembler domainListModelAssembler;

    public DomainListController(DomainListRepository domainListRepository, DomainListModelAssembler domainListModelAssembler) {
        this.domainListRepository = domainListRepository;
        this.domainListModelAssembler = domainListModelAssembler;
    }
    @GetMapping("/{id}")
    EntityModel<Domain> one(@PathVariable int id){
        Domain domain = domainListRepository.findById(id) //
        .orElseThrow(()-> new DomainListNotFoundException(id));
        return domainListModelAssembler.toModel(domain);
    }
    @GetMapping
    CollectionModel<EntityModel<Domain>> all(){
        List<EntityModel<Domain>> entityModels =domainListRepository.findAll().stream() //
        .map(domainListModelAssembler::toModel) //
        .collect(Collectors.toList());
        return CollectionModel.of(entityModels, linkTo(methodOn(DomainListController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newDomain(@RequestBody Domain domain){
        EntityModel<Domain> entityModel =domainListModelAssembler.toModel(domainListRepository.save(domain));
            return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateDomain(@RequestBody Domain domain ,@PathVariable int id){
        Domain updateDomains = domainListRepository.findById(id)//
            .map(domains->{
                domains.setName(domain.getName());
                domains.setWhiteBlackList(domain.getWhiteBlackList());
                return domainListRepository.save(domains);
            })//
            .orElseGet(()->{
               domain.setDomainCode(id);
               return domainListRepository.save(domain);
            });
        EntityModel<Domain> entityModel = domainListModelAssembler.toModel(updateDomains);
        return  ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(entityModel);
    }
}
