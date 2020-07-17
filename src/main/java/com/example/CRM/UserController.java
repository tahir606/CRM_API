package com.example.CRM;

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
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UsersModelAssembler assembler;
    public UserController(UserRepository userRepository, UsersModelAssembler assembler) {
        this.userRepository = userRepository;
        this.assembler=assembler;

    }
    @GetMapping("/{id}")
    EntityModel<Users> one(@PathVariable int id) {

        Users users = userRepository.findById(id)//
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(users);
    }
    @GetMapping
    CollectionModel<EntityModel<Users>> all() {

        List<EntityModel<Users>> users = userRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> newUser(@RequestBody Users user) {

        EntityModel<Users> entityModel = assembler.toModel(userRepository.save(user));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Users users, @PathVariable int id) {

        Users updatedUser = userRepository.findById(id) //
                .map(user -> {
                    user.setEmail(users.getEmail());
                    user.setFullName(users.getFullName());
                    user.setUserName(users.getUserName());
                    user.setUserPassword(users.getUserPassword());
                    user.setNote(users.getNote());
                    user.setUserRight(users.getUserRight());
                    return userRepository.save(user);
                }) //
                .orElseGet(() -> {
                    users.setUserCode(id);
                    return userRepository.save(users);
                });

        EntityModel<Users> entityModel = assembler.toModel(updatedUser);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable int id) {

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    /*
    @PostMapping ("/add")// Map ONLY POST Requests
    public String addNewUser(@RequestBody Users user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        System.out.println(user);
        userRepository.save(user);
        return "saved";
    }
*/

}
