package com.example.building_company.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private String category;
    @Column(name = "client_name")
    private String clientName;
    @Column(nullable = false, length = 755)
    private String description;
    @Column
    private String place;
    @Column(name = "title_image_link")
    private String titleImageLink;
    @ElementCollection
    @CollectionTable(name = "project_additional_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "additional_image_link")
    private List<String> additionalImages;
}
