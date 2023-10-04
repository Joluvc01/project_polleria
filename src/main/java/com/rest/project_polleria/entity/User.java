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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String username;

    @NotNull
    @Size(max = 50)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
