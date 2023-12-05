package com.example.building_company.model;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "phone_number")
    private String phoneNumber;
}
