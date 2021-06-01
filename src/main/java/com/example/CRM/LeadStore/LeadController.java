package com.example.CRM.LeadStore;

import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leads")
public class LeadController {
    private final LeadRepository leadRepository;
    private final LeadModelAssembler leadModelAssembler;
    private final EmailDBHandler emailDBHandler;

    public LeadController(LeadRepository leadRepository, LeadModelAssembler leadModelAssembler, EmailDBHandler emailDBHandler) {
        this.leadRepository = leadRepository;
        this.leadModelAssembler = leadModelAssembler;
        this.emailDBHandler = emailDBHandler;
    }

    @GetMapping("/{id}")
    EntityModel<Lead> one(@PathVariable int id) {
        Lead lead = leadRepository.findById(id)//
                .orElseThrow(() -> new LeadNotFoundException(id));
        return leadModelAssembler.toModel(lead);
    }
    @RequestMapping("/{id}")
    EntityModel<Lead> getLead(@PathVariable int id) {
        Lead lead = leadRepository.findById(id)//
                .orElseThrow(() -> new LeadNotFoundException(id));
        return leadModelAssembler.toModel(lead);
    }
    @GetMapping
    CollectionModel<EntityModel<Lead>> all() {
        List<EntityModel<Lead>> lead = leadRepository.findAll().stream() //
                .map(leadModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(lead, linkTo(methodOn(LeadController.class).all()).withSelfRel());
    }

    @RequestMapping("/getAllLeads")
    CollectionModel<EntityModel<Lead>> getAllLeads() {
        List<EntityModel<Lead>> lead = leadRepository.findAll().stream() //
                .map(leadModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(lead, linkTo(methodOn(LeadController.class).all()).withSelfRel());
    }

    @RequestMapping("/updateLead/{leadId}")
    public void updateLead(@PathVariable int leadId) {
        emailDBHandler.updateLead(leadId);
    }

    @RequestMapping("/addLead")
    ResponseEntity<?> addNewLead(@RequestBody Lead lead) {
        EntityModel<Lead> entityModel = leadModelAssembler.toModel(leadRepository.save(lead));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    @PostMapping
    ResponseEntity<?> newLeads(@RequestBody Lead lead) {
        EntityModel<Lead> entityModel = leadModelAssembler.toModel(leadRepository.save(lead));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateLeads(@RequestBody Lead lead, @PathVariable int id) {
        Lead leadsUpdate = leadRepository.findById(id)//
                .map(leads -> {
                    leads.setFirstName(lead.getFirstName());
                    leads.setLastName(lead.getLastName());
                    leads.setTittle(lead.getTittle());
                    leads.setCompanyName(lead.getCompanyName());
                    leads.setWebsite(lead.getWebsite());
                    leads.setCity(lead.getCity());
                    leads.setCountry(lead.getCountry());
                    leads.setNote(lead.getNote());
                    leads.setsOther(lead.getsOther());
                    leads.setConverted(lead.getConverted());
                    leads.setCreatedBy(lead.getCreatedBy());
                    leads.setCreatedOn(lead.getCreatedOn());
                    leads.setFreeze(lead.getFreeze());
                    return leadRepository.save(leads);
                })//
                .orElseGet(() -> {
                    lead.setLeadsId(id);
                    return leadRepository.save(lead);
                });
        EntityModel<Lead> leadEntityModel = leadModelAssembler.toModel(leadsUpdate);
        return ResponseEntity //
                .created(leadEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(leadEntityModel);
    }

}
