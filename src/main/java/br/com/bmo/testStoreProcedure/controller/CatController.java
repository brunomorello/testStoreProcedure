package br.com.bmo.testStoreProcedure.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.testStoreProcedure.model.Cat;
import br.com.bmo.testStoreProcedure.model.dto.CatInputDTO;
import br.com.bmo.testStoreProcedure.model.dto.CatsResponseDTO;
import br.com.bmo.testStoreProcedure.service.CatService;

@RestController
@RequestMapping("/api/v1/cat-sys/")
public class CatController {
	
	@Autowired
	private CatService service;

	@GetMapping("{id}")
	public ResponseEntity<Cat> findById(@PathVariable(value = "id") Long id) {
		Optional<Cat> catFoundOpt = service.findCatById(id);
		if (catFoundOpt.isPresent())
			return ResponseEntity.ok(catFoundOpt.get());
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CatInputDTO request, UriComponentsBuilder uriBuilder) {
		Cat createdCat = service.create(request.parse());
		URI uri = uriBuilder.path("/api/v1/cat-sys/{id}").buildAndExpand(createdCat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("older-than/{age}")
	public ResponseEntity<CatsResponseDTO> getCatsOlderThan(@PathVariable(value = "age") Integer age) {
		return ResponseEntity.ok(service.findCatsOlderThan(age));
	}
	
	@GetMapping("count-older-than/{age}")
	public ResponseEntity<Integer> countCatsOlderThan(@PathVariable(value = "age") Integer age) {
		return ResponseEntity.ok(service.countCatsOlderThan(age));
	}
	
	@GetMapping("older-than-simple/{age}")
	public ResponseEntity<CatsResponseDTO> getCatsOlderThanV2(@PathVariable(value = "age") Integer age) {
		return ResponseEntity.ok(service.findCatsOlderThanSimplified(age));
	}
	
}