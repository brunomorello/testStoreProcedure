package br.com.bmo.testStoreProcedure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bmo.testStoreProcedure.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long>, CatCustomRepository {

	Optional<Cat> findByNameLike(String name);
	
	@Procedure(procedureName = "FIND_CATS_OLDER_THAN_V2")
	List<Cat> getCatsOlderThanSimple(@Param("age_in") Integer age);
	
	@Query(value = "CALL COUNT_CATS_OLDER_THAN(:age_in);", nativeQuery = true)
	Integer getCountCatsOlderThan(@Param("age_in") Integer age);
}