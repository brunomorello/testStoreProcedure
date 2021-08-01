package br.com.bmo.testStoreProcedure.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bmo.testStoreProcedure.model.Cat;
import br.com.bmo.testStoreProcedure.model.dto.CatInputDTO;
import br.com.bmo.testStoreProcedure.service.CatService;

@RestController
@RequestMapping("/api/v1/catSys/")
public class CatController {
	
	@Autowired
	private CatService service;

	@GetMapping("{id}")
	public ResponseEntity<Cat> findById(@PathParam(value = "id") Long id) {
		Cat catFound = service.findCatById(id);
		return ResponseEntity.ok(catFound);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CatInputDTO request, UriComponentsBuilder uriBuilder) {
		Cat createdCat = service.create(request.parse());
		URI uri = uriBuilder.path("/api/v1/catSys/{id}").buildAndExpand(createdCat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}