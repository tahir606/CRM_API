package com.example.CRM.User;

import com.example.CRM.Email.EmailTicket.EmailTickets;
import com.example.CRM.JCode.EmailDBHandler;
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
    private final UserSystem userSystem;
    private final EmailDBHandler emailDBHandler;

    public UserController(UserRepository userRepository, UsersModelAssembler assembler, UserSystem userSystem, EmailDBHandler emailDBHandler) {
        this.userRepository = userRepository;
        this.assembler = assembler;
        this.userSystem = userSystem;
        this.emailDBHandler = emailDBHandler;
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


    @RequestMapping("/authentication")
    public Users authenticateLogin(@RequestParam String userName, @RequestParam String userPass) {
        Users users = userRepository.findByUserNameAndPasswordAndFreezeAndIsLog(userName, userPass, 0, 0);
        if (users!=null){
            users.setIsLog(1);
        }

        userSystem.updateUserStatus(users);

        return users;
    }


    @RequestMapping("/updateUser/{userCode}")
    public Users setLogin(@PathVariable int userCode, @RequestParam int isLog) {
        Users users = userRepository.findById(userCode)//
                .orElseThrow(() -> new UserNotFoundException(userCode));
        if (users!=null){
            users.setIsLog(isLog);
        }
        userSystem.updateUserStatus(users);

        return users;
    }

    @RequestMapping("/getUser/{id}")
    public Users getUser(@PathVariable int id) {

        Users users = userRepository.findById(id)//
                .orElseThrow(() -> new UserNotFoundException(id));

        return users;
    }
    @RequestMapping("/getUserName/{id}")
    public String getUserName(@PathVariable int id) {
        return  userRepository.findByFullName(id);
    }


    @RequestMapping("/getALlUsers")
    public CollectionModel<EntityModel<Users>> getAllUsers() {

        List<EntityModel<Users>> users = userRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestBody Users users) {
        return emailDBHandler.insertUser(users);

    }

    @RequestMapping("/getSolvedEmailsByUsers")
    public CollectionModel<EntityModel<Users>> getSolvedEmails(@RequestParam String filter) {
        String filterTicket = userSystem.filterTime(filter);
        List<EntityModel<Users>> users = emailDBHandler.getCountEmailStatus(filterTicket).stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());


    }
    @RequestMapping("/averageCalculate")
    public List<Users> averageCalculate() {
        List<Users> users = emailDBHandler.averageCalculate();

        return users;
    }
    @PutMapping("/{id}")
    ResponseEntity<?> replaceUser(@RequestBody Users users, @PathVariable int id) {

        Users updatedUser = userRepository.findById(id) //
                .map(user -> {
                    user.setEmail(users.getEmail());
                    user.setFullName(users.getFullName());
                    user.setUserName(users.getUserName());
                    user.setPassword(users.getPassword());
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

    @RequestMapping("/delete/{id}")
    ResponseEntity<?> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        emailDBHandler.deleteRightsChartByUserCode(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        userRepository.deleteById(id);
        emailDBHandler.deleteRightsChartByUserCode(id);
        return ResponseEntity.noContent().build();
    }

}
