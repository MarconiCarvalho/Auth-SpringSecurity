package com.example.auth.controllers;

import com.example.auth.domain.product.Product;
import com.example.auth.domain.product.ProductRequestDTO;
import com.example.auth.domain.product.ProductResponseDTO;
import com.example.auth.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;


    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.productRepository.save(newProduct);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity getAllProduct(){
        List<ProductResponseDTO> productList = this.productRepository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}