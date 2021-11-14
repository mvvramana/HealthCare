package com.app.service;

import java.util.List;

import com.app.entity.Specialization;

public interface ISpecializationService {
	public Long saveSpecialization(Specialization spec);

	public List<Specialization> getAllSpecialization();

	public void removeSpecialization(Long id);

	public Specialization getOneSpecializatio(Long id);

	public void updateSpecialization(Specialization spec);

	public boolean isSpecCodeExist(String specCode);

	public boolean isSpecNameExist(String specName);

	public boolean isSpecCodeExistForEdit(String specCode, Long id);

	public boolean isSpecNameExistForEdit(String specName, Long id);

}
