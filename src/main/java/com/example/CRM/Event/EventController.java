package com.example.CRM.Event;

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
@RequestMapping("/event_store")
public class EventController {

    private final EventRepository eventRepository;
    private final EventModelAssembler eventModelAssembler;


    public EventController(EventRepository eventRepository, EventModelAssembler eventModelAssembler) {
        this.eventRepository = eventRepository;
        this.eventModelAssembler = eventModelAssembler;
    }
    @GetMapping("/{id}")
    EntityModel<Event> one (@PathVariable int id){
        Event event =eventRepository.findById(id)//
        .orElseThrow(()-> new EventNotFoundException(id));
        return eventModelAssembler.toModel(event);
    }
    @GetMapping()
    CollectionModel<EntityModel<Event>> all(){
        List<EntityModel<Event>> events= eventRepository.findAll().stream() //
        .map(eventModelAssembler::toModel) //
        .collect(Collectors.toList());
        return CollectionModel.of(events,linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @PostMapping()
    ResponseEntity<?> insertEvent(@RequestBody Event event){
        EntityModel<Event> eventEntityModel =eventModelAssembler.toModel(eventRepository.save(event));
        return ResponseEntity //
                .created(eventEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(eventEntityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateEvent(@RequestBody Event event ,@PathVariable int id){
        Event eventsUpdate = eventRepository.findById(id)//
            .map(events -> {
                events.setTittle(event.getTittle());
                events.setLocation(event.getLocation());
                events.setEventAllDay(event.getEventAllDay());
                events.setFrom(event.getFrom());
                events.setTo(event.getTo());
                events.setDescription(event.getDescription());
                events.setNotified(event.getNotified());
                events.setDescription(event.getDescription());
                events.setClientID(event.getClientID());
                events.setLeadsId(event.getLeadsId());
                events.setProductID(event.getProductID());
                events.setCreatedOn(event.getCreatedOn());
                events.setCreatedBy(event.getCreatedBy());
                events.setFreeze(event.getFreeze());
                events.setClosedOn(event.getClosedOn());
                events.setStatus(event.getStatus());
                return eventRepository.save(events);
            })//
            .orElseGet(()->{
                event.setEventID(id);
                return eventRepository.save(event);
            });
        EntityModel<Event> eventEntityModel= eventModelAssembler.toModel(eventsUpdate);
        return ResponseEntity //
            .created(eventEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(eventEntityModel);
    }
}
