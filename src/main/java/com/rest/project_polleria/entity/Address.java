package com.rest.project_polleria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String city;

    @NotNull
    @Size(max = 50)
    private String state;

    @NotNull
    @Size(max = 50)
    private String province;

    @NotNull
    @Size(max = 50)
    private String district;

    @NotNull
    private String address;

    @NotNull
    @Size(max = 3)
    private String number;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
