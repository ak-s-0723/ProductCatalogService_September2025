package org.example.productcatalogservice_september2025.tableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value="TA")
public class Ta extends User {
    long hours;
}
