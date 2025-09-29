package org.example.productcatalogservice_september2025.tableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    long hours;
}
