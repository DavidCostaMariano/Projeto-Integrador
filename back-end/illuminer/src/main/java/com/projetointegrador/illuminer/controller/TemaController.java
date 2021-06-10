package com.projetointegrador.illuminer.controller;

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

import com.projetointegrador.illuminer.model.Tema;
import com.projetointegrador.illuminer.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin("*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity <List<Tema>> getAll (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById (@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<Tema>> getByTema (@PathVariable String tema){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(tema));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema) {
		if(tema.getId()!= null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tema> put (@RequestBody Tema tema, @PathVariable Long id) {
		if(!repository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		tema.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
