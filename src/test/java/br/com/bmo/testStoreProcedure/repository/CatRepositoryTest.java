package br.com.bmo.testStoreProcedure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.bmo.testStoreProcedure.model.Cat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CatRepositoryTest {
	
	@Autowired
	private CatRepository repository;

	@Test
	void whenFindingCatById_thenCorrect() {
		var cat = new Cat();
		cat.setName("Izzy");
		cat.setGender('F');
		cat.setAge(1);
		repository.save(cat);
		
		assertThat(repository.findById(1L)).isInstanceOf(Optional.class);
	}
	
	@Test
	void whenFindingAllCats_thenCorrect() {
		var luna = new Cat();
		luna.setName("Luna");
		luna.setAge(5);
		luna.setGender('F');
		
		var izzy = new Cat();
		izzy.setName("Izzy");
		izzy.setAge(1);
		izzy.setGender('F');
		
		repository.save(luna);
		repository.save(izzy);
		
		assertThat(repository.findAll()).isInstanceOf(List.class);
		
		List<Cat> allCats = repository.findAll();
		assertTrue(allCats.size() > 0);
	}
	
	@Test
	void whenSavingCat_thenCorrect() {
		var mel = new Cat();
		mel.setName("mel");
		mel.setAge(12);
		mel.setGender('F');
		
		repository.save(mel);
		
		Optional<Cat> catFound = repository.findByNameLike("mel");
		assertEquals("mel", catFound.get().getName());
	}
	
	@Test
	void whenFindingCatsOlderThanSpecificAge_thenCorrect() {
		List<Cat> listOfCatsOlderThan = repository.getCatsOlderThan(0);
		assertTrue(listOfCatsOlderThan.size() > 0);
	}

}