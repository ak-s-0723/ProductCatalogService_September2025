package org.example.productcatalogservice_september2025.tableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value="MENTOR")
public class Mentor extends User {
    double ratings;
}
