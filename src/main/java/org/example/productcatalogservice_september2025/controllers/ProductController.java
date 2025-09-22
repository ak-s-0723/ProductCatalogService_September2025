package org.example.productcatalogservice_september2025.controllers;

import org.example.productcatalogservice_september2025.dtos.CategoryDto;
import org.example.productcatalogservice_september2025.dtos.ProductDto;

import org.example.productcatalogservice_september2025.models.Category;
import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<ProductDto> productDtos = new ArrayList<>();
       List<Product> products = productService.getAllProducts();
       for(Product product :products) {
           productDtos.add(from(product));
       }

       return productDtos;
    }


   @PutMapping("/products/{id}")
   ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto) {
        Product input = from(productDto);
        Product updatedProduct = productService.replaceProduct(input,id);
        return from(updatedProduct);
   }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
            if (productId <= 0) {
                //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                throw new IllegalArgumentException("Please pass id > 0");
            }
            Product product = productService.getProductById(productId);
            if(product == null) {
                throw new RuntimeException("Something went wrong at our side");
            }
            ProductDto productDto = from(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategoryDto() != null) {
            Category category = new Category();
            category.setName(productDto.getCategoryDto().getName());
            product.setCategory(category);
        }
        return product;
    }
}
