package org.example.productcatalogservice_september2025.services;

import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty()) return null;

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOptional = productRepo.findById(product.getId());
        if(productOptional.isPresent()) return productOptional.get();

        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }
}
