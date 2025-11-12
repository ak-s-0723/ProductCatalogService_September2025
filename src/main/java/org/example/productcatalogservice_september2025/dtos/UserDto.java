package org.example.productcatalogservice_september2025.dtos;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String name;
    //private List<Role> roles = new ArrayList<>();
}
