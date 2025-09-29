package org.example.productcatalogservice_september2025.tableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User {
    String company;
}
