package com.projetointegrador.illuminer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.model.Comentario;
import com.projetointegrador.illuminer.repository.ComentarioRepository;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoComentario;

@RestController
@RequestMapping ("/comentarios")
@CrossOrigin ("*")
public class ComentarioController {
	
	@Autowired
	private ComentarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Comentario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Comentario> GetById(@PathVariable Long id){
		return repository.findById(id).map(rest -> ResponseEntity.ok(rest))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Comentario> post (@RequestBody @Valid Comentario comentario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentario));
	}
	
	@PutMapping
	public ResponseEntity<Comentario> put (@RequestBody @Validated(ValidationGroupAtualizacaoComentario.class) Comentario comentario) {
		if (!repository.existsById(comentario.getId())){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(repository.save(comentario));
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
