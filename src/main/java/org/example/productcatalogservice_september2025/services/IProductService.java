package org.example.productcatalogservice_september2025.services;

import org.example.productcatalogservice_september2025.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

}
