package org.example.productcatalogservice_september2025.controllers;

import org.example.productcatalogservice_september2025.dtos.SearchRequestDto;
import org.example.productcatalogservice_september2025.models.Product;
import org.example.productcatalogservice_september2025.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto)
    {
      return searchService.search(searchRequestDto.getQuery(),
              searchRequestDto.getPageSize(),
              searchRequestDto.getPageNumber(),
              searchRequestDto.getSortParamList());
    }
}


/*
{
	"query" : "laptop",
	"pageNumber" : 0,
	"pageSize" : 6,
	"sortParamList" : [
		{
		   "sortCriteria" : "price",
			 "sortType" : "ASC"
		},
		{
			 "sortCriteria" : "id",
			 "sortType" : "DESC"
		}
	]
}
 */
