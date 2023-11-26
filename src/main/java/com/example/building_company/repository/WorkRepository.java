package com.example.building_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.building_company.model.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {

}
