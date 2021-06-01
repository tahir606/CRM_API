package com.example.CRM.Event;

import com.example.CRM.JCode.EmailDBHandler;
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
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;
    private final EventModelAssembler eventModelAssembler;
    private final EmailDBHandler emailDBHandler;

    public EventController(EventRepository eventRepository, EventModelAssembler eventModelAssembler, EmailDBHandler emailDBHandler) {
        this.eventRepository = eventRepository;
        this.eventModelAssembler = eventModelAssembler;
        this.emailDBHandler = emailDBHandler;
    }

    @GetMapping("/{id}")
    EntityModel<Event> one(@PathVariable int id) {
        Event event = eventRepository.findById(id)//
                .orElseThrow(() -> new EventNotFoundException(id));
        return eventModelAssembler.toModel(event);
    }
    @RequestMapping("/getEventsByClientId/{clientId}")
    public CollectionModel<EntityModel<Event>> getEventsByClientId(@PathVariable int clientId) {

        List<EntityModel<Event>> note = emailDBHandler.findEventByClientId(clientId).stream() //
                .map(eventModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }
    @RequestMapping("/getEventsByLeadId/{leadId}")
    public CollectionModel<EntityModel<Event>> getEventsByLeadId(@PathVariable int leadId) {

        List<EntityModel<Event>> note = emailDBHandler.findEventByLeadId(leadId).stream() //
                .map(eventModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(note, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }
    @GetMapping()
    CollectionModel<EntityModel<Event>> all() {
        List<EntityModel<Event>> events = eventRepository.findAll().stream() //
                .map(eventModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(events, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }

    @RequestMapping("/getAllEvent")
    CollectionModel<EntityModel<Event>> getAllEvent() {
        List<EntityModel<Event>> events = eventRepository.findAll().stream() //
                .map(eventModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(events, linkTo(methodOn(EventController.class).all()).withSelfRel());
    }
    @RequestMapping("/addEvent")
    ResponseEntity<?> addEvent(@RequestBody Event event) {
        EntityModel<Event> eventEntityModel = eventModelAssembler.toModel(eventRepository.save(event));
        return ResponseEntity //
                .created(eventEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(eventEntityModel);
    }
    @PostMapping()
    ResponseEntity<?> insertEvent(@RequestBody Event event) {
        EntityModel<Event> eventEntityModel = eventModelAssembler.toModel(eventRepository.save(event));
        return ResponseEntity //
                .created(eventEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(eventEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateEvent(@RequestBody Event event, @PathVariable int id) {
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
                .orElseGet(() -> {
                    event.setEventID(id);
                    return eventRepository.save(event);
                });
        EntityModel<Event> eventEntityModel = eventModelAssembler.toModel(eventsUpdate);
        return ResponseEntity //
                .created(eventEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(eventEntityModel);
    }

    @RequestMapping("/deleteEvent/{eventId}")
    public boolean deleteEvent(@PathVariable int eventId) {
          emailDBHandler.deleteEvent(eventId);

        return true;
    }
}
