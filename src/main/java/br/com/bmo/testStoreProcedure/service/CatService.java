package br.com.bmo.testStoreProcedure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.testStoreProcedure.model.Cat;
import br.com.bmo.testStoreProcedure.model.dto.CatsResponseDTO;
import br.com.bmo.testStoreProcedure.repository.CatRepository;

@Service
public class CatService {

	@Autowired
	private CatRepository repository;
	
	public Optional<Cat> findCatById(Long id) {
		return repository.findById(id);
	}
	
	public CatsResponseDTO findCatsOlderThan(Integer age) {
		return new CatsResponseDTO(repository.getCatsOlderThan(age));
	}
	
	public Integer countCatsOlderThan(Integer age) {
		return repository.getCountCatsOlderThan(age);
	}

	public Cat create(Cat cat) {
		return repository.save(cat);
	}

	public CatsResponseDTO findCatsOlderThanSimplified(Integer age) {
		return new CatsResponseDTO(repository.getCatsOlderThanSimple(age));
	}

}