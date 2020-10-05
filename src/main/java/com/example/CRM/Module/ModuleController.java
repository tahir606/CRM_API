package com.example.CRM.Module;
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
@RequestMapping("/module_locking")
public class ModuleController {


    private final ModuleLockingRepository moduleLockingRepository;
    private final ModuleLockingModelAssembler moduleLockingModelAssembler;

    public ModuleController(ModuleLockingRepository moduleLockingRepository, ModuleLockingModelAssembler moduleLockingModelAssembler) {
        this.moduleLockingRepository = moduleLockingRepository;
        this.moduleLockingModelAssembler = moduleLockingModelAssembler;
    }


    @GetMapping("/{id}")
    EntityModel<ModuleLocking> one(@PathVariable int id) {

        ModuleLocking moduleLocking = moduleLockingRepository.findById(id)//
                .orElseThrow(() -> new ModuleLockingNotFoundException(id));

        return moduleLockingModelAssembler.toModel(moduleLocking);
    }
    @GetMapping
    CollectionModel<EntityModel<ModuleLocking>> all() {

        List<EntityModel<ModuleLocking>> entityModels = moduleLockingRepository.findAll().stream() //
                .map(moduleLockingModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(ModuleController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addModule(@RequestBody ModuleLocking moduleLocking) {

        EntityModel<ModuleLocking> entityModel = moduleLockingModelAssembler.toModel(moduleLockingRepository.save(moduleLocking));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateModule(@RequestBody ModuleLocking moduleLocking ,@PathVariable int id){
        ModuleLocking updateModule = moduleLockingRepository.findById(id)//
            .map(module ->{
                module.setDescription(moduleLocking.getDescription());
                module.setLockedTime(moduleLocking.getLockedTime());
                module.setPmID(moduleLocking.getPmID());
                module.setPsID(moduleLocking.getPsID());
                module.setUserCode(moduleLocking.getUserCode());
                module.setUnLockedTime(moduleLocking.getUnLockedTime());
                return moduleLockingRepository.save(module);
            })//
        .orElseGet(()->{
            moduleLocking.setModuleId(id);
            return moduleLockingRepository.save(moduleLocking);
        });
        EntityModel<ModuleLocking> moduleLockingEntityModel = moduleLockingModelAssembler.toModel(updateModule);
        return ResponseEntity //
            .created(moduleLockingEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(moduleLockingEntityModel);
    }
}
