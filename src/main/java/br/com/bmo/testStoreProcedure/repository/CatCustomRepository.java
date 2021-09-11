package br.com.bmo.testStoreProcedure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.bmo.testStoreProcedure.model.Cat;

@Repository
public interface CatCustomRepository {
	
	List<Cat> getCatsOlderThan(Integer age);
}
