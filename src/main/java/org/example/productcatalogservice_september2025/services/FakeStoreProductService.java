package org.example.productcatalogservice_september2025.services;

import org.example.productcatalogservice_september2025.clients.FakeStoreApiClient;
import org.example.productcatalogservice_september2025.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_september2025.models.Category;
import org.example.productcatalogservice_september2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
//                restTemplate.getForEntity("http://fakestoreapi.com/products/{id}",
//                        FakeStoreProductDto.class,id);
//
//        if(fakeStoreProductDtoResponseEntity.getStatusCode()
//                .equals(HttpStatusCode.valueOf(200)) &&
//                fakeStoreProductDtoResponseEntity.hasBody()) {
//            return from(fakeStoreProductDtoResponseEntity.getBody());

        FakeStoreProductDto  fakeStoreProductDto = fakeStoreApiClient.getProductById(id);
        if(fakeStoreProductDto!=null) {
            return from(fakeStoreProductDto);
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoListResponseEntity =
                restTemplate.getForEntity("http://fakestoreapi.com/products",FakeStoreProductDto[].class);

        if(fakeStoreProductDtoListResponseEntity.getStatusCode()
                .equals(HttpStatusCode.valueOf(200)) && fakeStoreProductDtoListResponseEntity.hasBody()) {
            FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreProductDtoListResponseEntity.getBody();
            for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                Product product = from(fakeStoreProductDto);
                products.add(product);
            }
        }
        return products;
    }

    //ToDo : Homework
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDto input = from(product);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.PUT,"http://fakestoreapi.com/products/{id}",
                input, FakeStoreProductDto.class,id);

        if(fakeStoreProductDtoResponseEntity.getStatusCode()
                .equals(HttpStatusCode.valueOf(200)) &&
                fakeStoreProductDtoResponseEntity.hasBody()) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }



    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }


    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return  product;
    }
}
