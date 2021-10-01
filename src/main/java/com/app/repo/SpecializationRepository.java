package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}
