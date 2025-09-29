package org.example.productcatalogservice_september2025.tableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User {
    double ratings;
}
