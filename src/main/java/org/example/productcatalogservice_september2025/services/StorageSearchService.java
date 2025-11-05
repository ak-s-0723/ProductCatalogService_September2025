package org.example.productcatalogservice_september2025.services;

import org.example.productcatalogservice_september2025.dtos.SortParam;
import org.example.productcatalogservice_september2025.dtos.SortType;
import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> search(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {
        //Sort sort = Sort.by("price").and(Sort.by("id").descending());

        Sort sort = null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = Sort.by(sortParams.get(0).getSortCriteria());
            else
                sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();
        }

        for (int i=1;i< sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC))
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
            else
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()).descending());
        }

        return productRepo.findByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
