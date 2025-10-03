package org.example.productcatalogservice_september2025.repos;

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

    @Test
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

}