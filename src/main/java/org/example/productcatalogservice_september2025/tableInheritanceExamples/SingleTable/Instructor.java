package org.example.productcatalogservice_september2025.tableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_instructor")
@DiscriminatorValue(value="INSTRUCTOR")
public class Instructor extends User {
    String company;
}
