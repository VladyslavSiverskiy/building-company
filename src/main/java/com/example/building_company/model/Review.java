package com.example.building_company.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "is_verified")
    private Boolean isVerified;
}
