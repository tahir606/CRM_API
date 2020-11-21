package com.example.CRM.Email.History;

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
@RequestMapping("/history")
public class HistoryController {
    private final HistoryRepository historyRepository;
    private final HistoryModelAssembler historyAssembler;

    public HistoryController(HistoryRepository historyRepository, HistoryModelAssembler historyAssembler) {
        this.historyRepository = historyRepository;
        this.historyAssembler = historyAssembler;
    }

    @GetMapping("/{id}")
    public EntityModel<TicketHistory> one(@PathVariable int id) {

        TicketHistory historyMaintain = historyRepository.findById(id)//
                .orElseThrow(() -> new HistoryNotFoundException(id));
        return historyAssembler.toModel(historyMaintain);
    }

    @RequestMapping("/view/{id}")
    public CollectionModel<EntityModel<TicketHistory>> getHistory(@PathVariable int id) {
        List<EntityModel<TicketHistory>> historyMaintain = historyRepository.findByEmailId(id).stream() //
                .map(historyAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(historyMaintain, linkTo(methodOn(HistoryController.class).all()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<TicketHistory>> all() {

        List<EntityModel<TicketHistory>> entityModels = historyRepository.findAll().stream() //
                .map(historyAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(HistoryController.class).all()).withSelfRel());
    }


    @PostMapping
    ResponseEntity<?> newUser(@RequestBody TicketHistory historyMaintains) {

        EntityModel<TicketHistory> entityModel = historyAssembler.toModel(historyRepository.save(historyMaintains));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
}
