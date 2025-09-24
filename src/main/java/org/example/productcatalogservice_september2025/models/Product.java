package org.example.productcatalogservice_september2025.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    //Business Specific Field
    boolean isPrime;

    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
