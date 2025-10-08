package org.example.productcatalogservice_september2025.controllers;

import org.example.productcatalogservice_september2025.dtos.ProductDto;
import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidId_ReturnsProductSuccessfully() {
        //Arrange
        Long productId = 2L;

        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");
        product.setPrice(100000D);
        when(productService.getProductById(productId)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(productId);

        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(productId,productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone",productDtoResponseEntity.getBody().getName());
        assertEquals(100000D,productDtoResponseEntity.getBody().getPrice());
    }

    @Test
    public void TestGetProductById_WithInvalidId_ResultsInIllegalArgumentException() {
        //Arrange
        Long productId = -1L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()->productController.getProductById(productId));

        assertEquals("Illegal Id passed", exception.getMessage());
    }

    @Test
    public void TestGetProductById_WhereProductServiceThrowsRuntimeException_ResultsInRuntimeException() {
        //Arrange
        Long productId = 30000L;
        when(productService.getProductById(productId)).
                thenThrow(new RuntimeException("something went bad"));

        //Act and Assert
        assertThrows(RuntimeException.class,()->productController.getProductById(productId));
    }

}