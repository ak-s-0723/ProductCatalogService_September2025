package org.example.productcatalogservice_september2025.repos;

import org.example.productcatalogservice_september2025.models.Category;
import org.example.productcatalogservice_september2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    //@Test
    void testQueries() {
       // List<Product> productList = productRepo.findAll();
       // System.out.println(productList.get(0));
//        List<Product> productList = productRepo.findProductByPriceBetween(1D,90000D);
//        System.out.println(productList.size());
//        System.out.println(productList.get(0).getId());

//        List<Product> productList = productRepo.findProductByIsPrime(true);
//        System.out.println(productList.size());
        //System.out.println(productList.get(0).getId());

       // List<Product> productList = productRepo.findProductByOrderByPrice();
       // System.out.println(productList.get(0).getPrice());


        System.out.println(productRepo.findProductDescriptionById(1L));

    }

    @Test
    public void addDataToAWSTables() {
        Product product1  = new Product();
        product1.setId(10L);
        product1.setName("Melody");
        product1.setPrice(2D);
        Category category = new Category();
        category.setId(101L);
        category.setName("Toffees");
        product1.setCategory(category);

        Product product2  = new Product();
        product2.setId(12L);
        product2.setName("Eclairs");
        product2.setPrice(1D);
        product2.setCategory(category);
        productRepo.save(product1);
        productRepo.save(product2);

    }

}