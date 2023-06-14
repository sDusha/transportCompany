package com.example.transportcompany.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;

    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private String contactInfo;
}
