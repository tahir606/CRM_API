package com.example.CRM.NotificationSettings;

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
@RequestMapping("/notification_settings")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final NotificationModelAssembler notificationModelAssembler;

    public NotificationController(NotificationRepository notificationRepository, NotificationModelAssembler notificationModelAssembler) {
        this.notificationRepository = notificationRepository;
        this.notificationModelAssembler = notificationModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Notification> one(@PathVariable int id) {

        Notification notification = notificationRepository.findById(id)//
                .orElseThrow(() -> new NotificationNotFoundException(id));

        return notificationModelAssembler.toModel(notification);
    }
    @GetMapping
    CollectionModel<EntityModel<Notification>> all() {

        List<EntityModel<Notification>> notification = notificationRepository.findAll().stream() //
                .map(notificationModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(notification, linkTo(methodOn(NotificationController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newUser(@RequestBody Notification notification) {

        EntityModel<Notification> entityModel = notificationModelAssembler.toModel(notificationRepository.save(notification));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateNotification(@RequestBody Notification notification,@PathVariable int id ){
        Notification notificationUpdate = notificationRepository.findById(id)//
            .map(notifications -> {
                notifications.setEvent(notification.getEvent());
                notifications.setTask(notification.getTask());
                return notificationRepository.save(notifications);
            })//
        .orElseGet(()->{
            notification.setNotificationId(id);
            return notificationRepository.save(notification);
        });
        EntityModel<Notification> notificationEntityModel = notificationModelAssembler.toModel(notificationUpdate);
        return ResponseEntity //
            .created(notificationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(notificationEntityModel);
    }
}
