package br.com.bmo.testStoreProcedure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bmo.testStoreProcedure.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
	Optional<Cat> findByNameLike(String name);
}
