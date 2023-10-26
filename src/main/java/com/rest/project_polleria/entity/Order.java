package com.rest.project_polleria.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private double subtotal;
    private double igv;
    private double totalFinal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order_Detail> detalles = new HashSet<>();

    public void calculateTotalFinal() {
        totalFinal = detalles.stream().mapToDouble(Order_Detail::getTotal).sum();
    }

    public void calculateSubtotalIgv() {
        calculateTotalFinal();
        igv = totalFinal * 0.18;
        subtotal = totalFinal - igv;

        // Redondear el valor del IGV a dos decimales
        igv = Math.round(igv * 100.0) / 100.0;
    }
}

