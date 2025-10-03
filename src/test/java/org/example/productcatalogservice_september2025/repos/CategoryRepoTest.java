package org.example.productcatalogservice_september2025.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_september2025.models.Category;
import org.example.productcatalogservice_september2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testLoading() {
        Category category = categoryRepo.findById(1L).get();
        System.out.println(category.getName());
        for(Product product : category.getProducts()) {
            System.out.println(product.getName());
        }
    }


    @Test
    @Transactional
    public void testSomething() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category : categoryList) {
            System.out.println(category.getName());
            for(Product product : category.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }
}