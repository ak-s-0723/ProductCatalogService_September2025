package org.example.productcatalogservice_september2025.services;

import org.example.productcatalogservice_september2025.dtos.SortParam;
import org.example.productcatalogservice_september2025.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> search(String query, Integer pageSize, Integer pageNumber,List<SortParam> sortParams);
}
