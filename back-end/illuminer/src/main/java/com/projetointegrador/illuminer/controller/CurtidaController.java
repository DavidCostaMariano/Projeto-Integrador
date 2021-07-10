package com.projetointegrador.illuminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.model.CurtidaPK;
import com.projetointegrador.illuminer.repository.CurtidaRepository;

@RestController
@RequestMapping ("/curtida")
@CrossOrigin ("*")
public class CurtidaController {
	
	@Autowired
	private CurtidaRepository repository;
	
	@DeleteMapping("/{id")
	public ResponseEntity<Void> delete (@PathVariable CurtidaPK id){
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
