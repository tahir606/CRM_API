package com.example.CRM.Product;

import com.example.CRM.Module.*;
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
@RequestMapping("/product_module")
public class ProductModuleController {

    private final ProductModuleRepository productModuleRepository;
    private final ProductModuleModelAssembler productModuleModelAssembler;

    public ProductModuleController(ProductModuleRepository productModuleRepository, ProductModuleModelAssembler productModuleModelAssembler) {
        this.productModuleRepository = productModuleRepository;
        this.productModuleModelAssembler = productModuleModelAssembler;
    }


    @GetMapping("/{id}")
    EntityModel<ProductModule> one(@PathVariable int id) {

        ProductModule productModule = productModuleRepository.findById(id)//
                .orElseThrow(() -> new ProductModuleNotFoundException(id));

        return productModuleModelAssembler.toModel(productModule);
    }
    @GetMapping
    CollectionModel<EntityModel<ProductModule>> all() {

        List<EntityModel<ProductModule>> entityModels = productModuleRepository.findAll().stream() //
                .map(productModuleModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(ProductModuleController.class).all()).withSelfRel());
    }

    @PostMapping
    ResponseEntity<?> addModule(@RequestBody ProductModule productModule) {

        EntityModel<ProductModule> entityModel = productModuleModelAssembler.toModel(productModuleRepository.save(productModule));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateProductModule(@RequestBody ProductModule productModule , @PathVariable int id){
        ProductModule productModuleUpdate = productModuleRepository.findById(id)//
            .map(product -> {
                product.setCreatedBy(productModule.getCreatedBy());
                product.setCreatedOn(productModule.getCreatedOn());
                product.setDescription(productModule.getDescription());
                product.setName(productModule.getName());
                product.setPsID(productModule.getPsID());
                return productModuleRepository.save(product);
            })//
            .orElseGet(()->{
               productModule.setPmID(id);
               return productModuleRepository.save(productModule);
            });
        EntityModel<ProductModule> productModuleEntityModel = productModuleModelAssembler.toModel(productModuleUpdate);
        return ResponseEntity //
            .created(productModuleEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
            .body(productModuleEntityModel);
    }
}
