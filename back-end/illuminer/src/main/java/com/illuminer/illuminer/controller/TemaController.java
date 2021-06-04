package com.illuminer.illuminer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.illuminer.illuminer.model.Tema;
import com.illuminer.illuminer.repository.TemaRepository;

@RestController
@RequestMapping ("/temas")
@CrossOrigin ("*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable Long id){
		return repository.findById(id).map(rest -> ResponseEntity.ok(rest))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/tema/{tema}")
	public ResponseEntity<List<Tema>> GetByTema(@PathVariable String tema) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(tema));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema) {
		if(tema.getId()!= null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<Tema> put (@RequestBody Tema tema, @PathVariable Long id) {
		if (!repository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		tema.setId(id);
		return ResponseEntity.ok(repository.save(tema));
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		if (!repository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}