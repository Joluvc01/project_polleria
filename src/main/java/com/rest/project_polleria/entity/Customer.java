package com.rest.project_polleria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "customer")
public class Customer {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String photo;
    private Set<Order> orders = new HashSet<>(); //many to one relationship with Order>

}
