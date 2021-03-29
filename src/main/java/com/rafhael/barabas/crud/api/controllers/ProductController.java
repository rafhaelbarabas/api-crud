package com.rafhael.barabas.crud.api.controllers;

import com.rafhael.barabas.crud.services.ProductService;
import com.rafhael.barabas.crud.data.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final PagedResourcesAssembler<ProductVO> assembler;

    @Autowired
    public ProductController(ProductService service, PagedResourcesAssembler<ProductVO> assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    private void createLink(ProductVO productVO) {
        productVO.add(linkTo(methodOn(ProductController.class).findById(productVO.getId())).withSelfRel());
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sort = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort, "name"));
        Page<ProductVO> products = service.findAll(pageable);
        products.forEach(this::createLink);
        PagedModel<EntityModel<ProductVO>> pagedModel = assembler.toModel(products);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagedModel);
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ProductVO> findById(@PathVariable("id") Long id) {
        ProductVO productVO = service.findById(id);
        createLink(productVO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productVO);
    }

    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ProductVO> create(@RequestBody ProductVO productVO) {
        ProductVO productVOSave = service.create(productVO);
        createLink(productVOSave);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productVOSave);
    }

    @PutMapping(consumes = {"application/json", "application/xml", "application/x-yaml"},
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ProductVO> update(@RequestBody ProductVO productVO) {
        ProductVO productVOUpdated = service.update(productVO);
        createLink(productVOUpdated);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productVOUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
