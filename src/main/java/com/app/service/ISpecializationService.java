package com.app.service;

import java.util.List;

import com.app.entity.Specialization;

public interface ISpecializationService {
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecialization();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecializatio(Long id);
	public void updateSpecialization(Specialization spec);
	

}
