package com.example.CRM.Task;

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
@RequestMapping("/task_store")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskModelAssembler taskModelAssembler;

    public TaskController(TaskRepository taskRepository, TaskModelAssembler taskModelAssembler) {
        this.taskRepository = taskRepository;
        this.taskModelAssembler = taskModelAssembler;
    }

    @GetMapping("/{id}")
    EntityModel<Task> one(@PathVariable int id) {

        Task task = taskRepository.findById(id)//
                .orElseThrow(() -> new TaskNotFoundException(id));

        return taskModelAssembler.toModel(task);
    }
    @GetMapping
    CollectionModel<EntityModel<Task>> all() {

        List<EntityModel<Task>> task = taskRepository.findAll().stream() //
                .map(taskModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(task, linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addTask(@RequestBody Task task) {

        EntityModel<Task> entityModel = taskModelAssembler.toModel(taskRepository.save(task));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable int id ){
        Task taskUpdate = taskRepository.findById(id)//
            .map(tasks -> {
                tasks.setClientID(task.getClientID());
                tasks.setClosedOn(task.getClosedOn());
                tasks.setContactID(task.getContactID());
                tasks.setCreatedBy(task.getCreatedBy());
                tasks.setCreatedOn(task.getCreatedOn());
                tasks.setDescription(task.getDescription());
                tasks.setFreeze(task.getFreeze());
                tasks.setDueDate(task.getDueDate());
                tasks.setEntryDate(task.getEntryDate());
                tasks.setLeadsId(task.getLeadsId());
                tasks.setNotified(task.getNotified());
                tasks.setPsID(task.getPsID());
                tasks.setRepeat(task.getRepeat());
                tasks.setStatus(task.getStatus());
                tasks.setSubject(task.getSubject());
                return taskRepository.save(tasks);
            })//
            .orElseGet(()->{
                task.setTaskID(id);
                return taskRepository.save(task);
            });
        EntityModel<Task> taskEntityModel = taskModelAssembler.toModel(taskUpdate);
        return ResponseEntity//
            .created(taskEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(taskEntityModel);
    }
}
