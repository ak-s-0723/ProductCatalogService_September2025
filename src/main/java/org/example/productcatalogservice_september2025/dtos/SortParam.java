package org.example.productcatalogservice_september2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SortParam {
    private String sortCriteria;
    private SortType sortType;
}
