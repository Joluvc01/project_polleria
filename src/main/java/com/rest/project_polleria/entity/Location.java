package com.rest.project_polleria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 30)
    private String state;

    @NotNull
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 50)
    private String district;

    @NotNull
    private String address;

    @NotNull
    private String latitude;

    @NotNull
    private String altitude;

    @NotNull
    @Size(max = 9)
    private String contactNumber;

    @NotNull
    private String image;
}
