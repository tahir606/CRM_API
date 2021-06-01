package com.example.CRM.Product.Store;

import com.example.CRM.Product.*;
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
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductModelAssembler productModelAssembler;

    public ProductController(ProductRepository productRepository, ProductModelAssembler productModelAssembler) {
        this.productRepository = productRepository;
        this.productModelAssembler = productModelAssembler;
    }


    @GetMapping("/{id}")
    EntityModel<Product> one(@PathVariable int id) {

        Product product = productRepository.findById(id)//
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productModelAssembler.toModel(product);
    }

    @GetMapping
    CollectionModel<EntityModel<Product>> all() {

        List<EntityModel<Product>> entityModels = productRepository.findAll().stream() //
                .map(productModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @RequestMapping("/getAllProducts")
    CollectionModel<EntityModel<Product>> getAllProducts() {

        List<EntityModel<Product>> entityModels = productRepository.findAll().stream() //
                .map(productModelAssembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @RequestMapping("/{id}")
    EntityModel<Product> getProduct(@PathVariable int id) {

        Product product = productRepository.findById(id)//
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productModelAssembler.toModel(product);
    }

    @RequestMapping("/addProduct")
    ResponseEntity<?> addProduct(@RequestBody Product product) {

        EntityModel<Product> entityModel = productModelAssembler.toModel(productRepository.save(product));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @PostMapping
    ResponseEntity<?> addProductInStore(@RequestBody Product product) {

        EntityModel<Product> entityModel = productModelAssembler.toModel(productRepository.save(product));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable int id) {
        Product productUpdate = productRepository.findById(id)//
                .map(products -> {
                    products.setCreatedBy(product.getCreatedBy());
                    products.setCreatedOn(product.getCreatedOn());
                    products.setDescription(product.getDescription());
                    products.setName(product.getName());
                    products.setPrice(product.getPrice());
                    products.setProperty(product.getProperty());
                    products.setStarted(product.getStarted());
                    products.setType(product.getType());
                    products.setStatus(product.getStatus());
                    return productRepository.save(products);
                })//
                .orElseGet(() -> {
                    product.setPsID(id);
                    return productRepository.save(product);
                });
        EntityModel<Product> productEntityModel = productModelAssembler.toModel(productUpdate);
        return ResponseEntity //
                .created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(productEntityModel);
    }
}
