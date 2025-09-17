package org.example.productcatalogservice_september2025.controllers;

import org.example.productcatalogservice_september2025.models.Product;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/products/{id}")
      Product getProductById(@PathVariable("id") Long productId) {
      Product product =  new Product();
      product.setId(productId);
      return product;
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody Product input) {
      return input;
    }

}
