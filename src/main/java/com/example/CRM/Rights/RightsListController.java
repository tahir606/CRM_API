package com.example.CRM.Rights;

import com.example.CRM.User.UserController;
import com.example.CRM.User.UserNotFoundException;
import com.example.CRM.User.Users;
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
@RequestMapping("/rights_list")
public class RightsListController {

    private final RightsListRepository rightsListRepository;
    private final RightsListModelAssembler rightsListModelAssembler;

    public RightsListController(RightsListRepository rightsListRepository, RightsListModelAssembler rightsListModelAssembler) {
        this.rightsListRepository = rightsListRepository;
        this.rightsListModelAssembler = rightsListModelAssembler;
    }
    @GetMapping("/{id}")
    EntityModel<RightsList> one(@PathVariable int id) {

        RightsList rightsList = rightsListRepository.findById(id)//
                .orElseThrow(() -> new RightsListNotFoundException(id));

        return rightsListModelAssembler.toModel(rightsList);
    }
    @GetMapping
    CollectionModel<EntityModel<RightsList>> all() {

        List<EntityModel<RightsList>> rightsList = rightsListRepository.findAll().stream() //
                .map(rightsListModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(rightsList, linkTo(methodOn(RightsListController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addRightsList(@RequestBody RightsList rightsList) {

        EntityModel<RightsList> entityModel = rightsListModelAssembler.toModel(rightsListRepository.save(rightsList));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateRightList(@RequestBody RightsList rightsList, @PathVariable int id) {
        RightsList rightsListUpdate = rightsListRepository.findById(id)//
            .map(rights -> {
                rights.setFreeze(rightsList.getFreeze());
                rights.setName(rightsList.getName());
                return rightsListRepository.save(rights);
            })//
            .orElseGet(()->{
                rightsList.setRightsCode(id);
                return rightsListRepository.save(rightsList );
            });
        EntityModel<RightsList> rightsListEntityModel = rightsListModelAssembler.toModel(rightsListUpdate);
        return ResponseEntity//
            .created(rightsListEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(rightsListEntityModel);
    }
}

