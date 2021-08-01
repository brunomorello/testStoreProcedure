package br.com.bmo.testStoreProcedure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bmo.testStoreProcedure.model.Cat;
import br.com.bmo.testStoreProcedure.repository.CatRepository;

@Service
public class CatService {

	@Autowired
	private CatRepository repository;
	
	public Cat findCatById(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	public Cat create(Cat cat) {
		return repository.save(cat);
	}

}