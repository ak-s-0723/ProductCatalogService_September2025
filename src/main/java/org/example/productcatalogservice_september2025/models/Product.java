package org.example.productcatalogservice_september2025.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;

    //Business Specific Field
    boolean isPrime;

    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
