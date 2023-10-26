package com.rest.project_polleria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "order_detail")
public class Order_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private int quantity;

    private double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Product product;
    public void calculateTotal() {
        total = quantity * product.getPrice();
    }
}
