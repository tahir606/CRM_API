package com.example.CRM.Email.Setiings;

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
@RequestMapping("/settings")
public class SettingsController {
    private final SettingRepository settingRepository;
    private final SettingModelAssembler settingModelAssembler;

    public SettingsController(SettingRepository settingRepository, SettingModelAssembler settingModelAssembler) {
        this.settingRepository = settingRepository;
        this.settingModelAssembler = settingModelAssembler;
    }
    @GetMapping("/{id}")
    public EntityModel<EmailSettings> one(@PathVariable int id){
        EmailSettings emailSettings = settingRepository.findById(id)//
                .orElseThrow(()-> new SettingsNotFoundException(id));
        return settingModelAssembler.toModel(emailSettings);
    }

    @GetMapping
    public CollectionModel<EntityModel<EmailSettings>> all(){
        List<EntityModel<EmailSettings>> emailSettings = settingRepository.findAll().stream()//
            .map(settingModelAssembler::toModel)//
            .collect(Collectors.toList());
        return CollectionModel.of(emailSettings, linkTo(methodOn(SettingsController.class).all()).withSelfRel());
    }


    @PostMapping
    public ResponseEntity<?> insertSettings(@RequestBody EmailSettings emailSettings) {
        EntityModel<EmailSettings> entityModel = settingModelAssembler.toModel(settingRepository.save(emailSettings));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateSettings(@RequestBody EmailSettings emailSettings, @PathVariable int id) {
        EmailSettings settingsUpdate = settingRepository.findById(id)//
                .map(settings -> {
                    settings.setEmail(emailSettings.getEmail());
                    settings.setHost(emailSettings.getHost());
                    settings.setPassword(emailSettings.getPassword());
                    settings.setAutoCheck(emailSettings.getAutoCheck());
                    settings.setAutoText(emailSettings.getAutoText());
                    settings.setDisclaimerCheck(emailSettings.getDisclaimerCheck());
                    settings.setDisclaimerText(emailSettings.getDisclaimerText());
                    settings.setFilePath(emailSettings.getFilePath());
                    settings.setGeneraEmail(emailSettings.getGeneraEmail());
                    settings.setReplacementKeyword(emailSettings.getReplacementKeyword());
                    settings.setSolveCheck(emailSettings.getSolveCheck());
                    settings.setSolveText(emailSettings.getSolveText());
                    return settingRepository.save(settings);
                })//
                .orElseGet(() -> {
                    emailSettings.setCode(id);
                    return settingRepository.save(emailSettings);
                });
        EntityModel<EmailSettings> entityModel = settingModelAssembler.toModel(settingsUpdate);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }
}
