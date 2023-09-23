package com.CusoUnivem.Curso.controller;

import com.CusoUnivem.Curso.dto.ProductDto;
import com.CusoUnivem.Curso.model.UserModel;
import com.CusoUnivem.Curso.service.UserService;
import jakarta.validation.Valid;
import com.CusoUnivem.Curso.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CusoUnivem.Curso.service.ProductService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;

    final UserService userService;

    public ProductController(UserService service) {
        this.userService = service;
    }


    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){

        Optional<UserModel> userModelOptional = userService.findByEmail(productDto.getEmail());
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este email não é valido.");
        }
        var productModel = new ProductModel();
        productModel = productDto.convert(userService);

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }


    @GetMapping
    public ResponseEntity<Page<ProductModel>> getAllProduct(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable UUID id){
        Optional<ProductModel> productModelOptional = productService.findById(id);

        if(!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto para deletar não encontrado.");
        }

        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductDto productDto){
        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado para modificação.");
        }

        var productModel = new ProductModel();
        productModel = productDto.convert(userService);
        productModel.setId(productModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
}