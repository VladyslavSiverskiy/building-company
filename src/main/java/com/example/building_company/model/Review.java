package com.example.building_company.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Table(name = "reviews")
public class Review {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column
    private String content;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "is_verified")
    private Boolean isVerified;

    public Review() {
    }

    public Review(Long id, String content, LocalDateTime creationTime, Boolean isVerified) {
        this.id = id;
        this.content = content;
        this.creationTime = creationTime;
        this.isVerified = isVerified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
