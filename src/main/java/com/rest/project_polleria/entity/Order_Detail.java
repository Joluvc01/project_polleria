package com.rest.project_polleria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

public class Order_Detail {

    private UUID id;
    private int quantity;
    private double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Product product;
}
