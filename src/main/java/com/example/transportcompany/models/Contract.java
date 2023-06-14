package com.example.transportcompany.models;

import com.example.transportcompany.models.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.Double.max;
import static java.lang.Math.abs;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromAddress;
    @Column(nullable = false)
    private String toAddress;

    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double volume;

    private boolean Brittle;
    private boolean Express;
    private boolean Liquid;
    private boolean Gas;

    private double price;
    private String contactInfo;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private ContractStatus status;

    @PrePersist
    private void init() {
        // изначальный статус
        status = ContractStatus.FromAdress;

        // условная цена перевозки между городами
        price = max(weight, volume * 30) * max(abs(fromAddress.length() % 131 - toAddress.length() % 131), 1) *
                ((Brittle ? 1 : 0) * 10 + (Express ? 1 : 0) * 10 + (Liquid ? 1 : 0) * 10 + (Gas ? 1 : 0) * 10);
    }
}

