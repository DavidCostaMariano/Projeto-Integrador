package com.projetointegrador.illuminer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.model.Curtida;
import com.projetointegrador.illuminer.model.CurtidaPK;
import com.projetointegrador.illuminer.repository.CurtidaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping ("/curtidas")
@CrossOrigin ("*")
public class CurtidaController {
	
	@Autowired
	private CurtidaRepository repository;
	
	@PostMapping
	public ResponseEntity<Curtida> post (@RequestBody @Valid Curtida curtidas) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(curtidas));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable CurtidaPK id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
