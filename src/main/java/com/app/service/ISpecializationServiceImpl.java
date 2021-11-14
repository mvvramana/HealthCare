package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Specialization;
import com.app.exception.SpecializationNotFoundException;
import com.app.repo.SpecializationRepository;

@Service
public class ISpecializationServiceImpl implements ISpecializationService {
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		// repo.deleteById(id);
		repo.delete(getOneSpecializatio(id));

	}

	@Override
	public Specialization getOneSpecializatio(Long id) {
		/*
		 * Optional<Specialization> optional = repo.findById(id); if
		 * (optional.isPresent()) { return optional.get(); } else { throw new
		 * SpecializationNotFoundException(id+" NotFound"); }
		 */
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id + " NotFound"));

	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);

	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		Integer count = repo.getSpecCodeCount(specCode);
		boolean exist = count > 0 ? true : false;
		return exist;
		/* return repo.getSpecCodeCount(specCode)>0; */
	}

	@Override
	public boolean isSpecNameExist(String specName) {
		Integer count = repo.getSpecNameCount(specName);
		return count > 0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		return repo.getSpecCodeCountEdit(specCode, id) > 0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String specName, Long id) {
		return repo.getSpecNameCountEdit(specName, id) > 0;
	}

}
