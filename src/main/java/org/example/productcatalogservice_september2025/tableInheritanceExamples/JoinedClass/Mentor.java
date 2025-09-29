package org.example.productcatalogservice_september2025.tableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name="user_id_")
public class Mentor extends User {
    double ratings;
}
