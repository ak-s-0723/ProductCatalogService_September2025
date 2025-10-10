package org.example.productcatalogservice_september2025.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_september2025.dtos.ProductDto;
import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProducts_RunSuccessfully() throws Exception {
        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("MacBook Pro");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("MacBook Air");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productService.getAllProducts()).thenReturn(productList);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("MacBook Pro");

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("MacBook Air");

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        productDtos.add(productDto2);

        String body = objectMapper.writeValueAsString(productDtos);

        //Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(body))
                .andExpect(jsonPath("$[0].name").value("MacBook Pro"))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void TestCreateProduct_RunSuccessfully() throws Exception {
        //Arrange
        //anurag_Created_me_in_ut_7:25
        Product product = new Product();
        product.setName("Apple Watch");
        product.setId(10L);
        product.setPrice(40000D);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setPrice(40000D);
        productDto.setId(10L);
        productDto.setName("Apple Watch");

        //Act and Assert
        String dtoInString = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .content(dtoInString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(dtoInString))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("Apple Watch"));

    }
}


//{
//    "name" : "Apple Watch",
//        "price" :"40000",
//        "id" : "10"
//}