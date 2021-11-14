package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	@Query("select count(specCode) from Specialization where specCode=:specCode ")
	Integer getSpecCodeCount(String specCode);

	@Query("select count(specCode) from Specialization where specCode=:specCode and id!=:id")
	Integer getSpecCodeCountEdit(String specCode, Long id);

	@Query("select count(specName) from Specialization where specName=:specName ")
	Integer getSpecNameCount(String specName);

	@Query("select count(specName) from Specialization where specName=:specName and id!=:id")
	Integer getSpecNameCountEdit(String specName, Long id);

}
