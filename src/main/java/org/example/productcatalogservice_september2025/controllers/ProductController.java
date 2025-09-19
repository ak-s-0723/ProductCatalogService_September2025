package org.example.productcatalogservice_september2025.controllers;

import org.example.productcatalogservice_september2025.dtos.CategoryDto;
import org.example.productcatalogservice_september2025.dtos.ProductDto;

import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/products")
    List<ProductDto> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        try {
            if (productId <= 0) {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            Product product = productService.getProductById(productId);
            ProductDto productDto = from(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            //headers.add("showing to","learners");
            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products")
    ProductDto createProduct(@RequestBody ProductDto input) {
        return input;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategoryDto(categoryDto);
        }

        return productDto;
    }
}
