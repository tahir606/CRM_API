package com.example.CRM.Product;

import com.example.CRM.JCode.EmailDBHandler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productModule")
public class ProductModuleController {

    private final ProductModuleRepository repository;
    private final ProductModuleModelAssembler assembler;
    private final EmailDBHandler emailDBHandler;

    public ProductModuleController(ProductModuleRepository repository, ProductModuleModelAssembler assembler, EmailDBHandler emailDBHandler) {
        this.repository = repository;
        this.assembler = assembler;
        this.emailDBHandler = emailDBHandler;
    }


    @GetMapping("/{id}")
    EntityModel<ProductModule> one(@PathVariable int id) {

        ProductModule productModule = repository.findById(id)//
                .orElseThrow(() -> new ProductModuleNotFoundException(id));

        return assembler.toModel(productModule);
    }

    @GetMapping
    CollectionModel<EntityModel<ProductModule>> all() {

        List<EntityModel<ProductModule>> entityModels = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(ProductModuleController.class).all()).withSelfRel());
    }

    @RequestMapping("/addProductModule")
    ResponseEntity<?> addProductModule(@RequestBody ProductModule productModule) {

        EntityModel<ProductModule> entityModel = assembler.toModel(repository.save(productModule));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @RequestMapping("/updateProductModule")
    ResponseEntity<?> updateProductModule(@RequestBody ProductModule productModule) {

       int isUpdate= emailDBHandler.updateProductModule(productModule);
       if (isUpdate==0){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

        return new ResponseEntity<>(productModule.getPmID(),HttpStatus.OK);
    }



    @RequestMapping("/delete/{code}")
    ResponseEntity<Integer> delete(@PathVariable int code) {

        int isRemoved = emailDBHandler.deleteProductModule(code);
        if (isRemoved == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addModule(@RequestBody ProductModule productModule) {

        EntityModel<ProductModule> entityModel = assembler.toModel(repository.save(productModule));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateProductModule(@RequestBody ProductModule productModule, @PathVariable int id) {
        ProductModule productModuleUpdate = repository.findById(id)//
                .map(product -> {
                    product.setCreatedBy(productModule.getCreatedBy());
                    product.setCreatedOn(productModule.getCreatedOn());
                    product.setDescription(productModule.getDescription());
                    product.setName(productModule.getName());
                    product.setPsID(productModule.getPsID());
                    return repository.save(product);
                })//
                .orElseGet(() -> {
                    productModule.setPmID(id);
                    return repository.save(productModule);
                });
        EntityModel<ProductModule> productModuleEntityModel = assembler.toModel(productModuleUpdate);
        return ResponseEntity //
                .created(productModuleEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(productModuleEntityModel);
    }
}
