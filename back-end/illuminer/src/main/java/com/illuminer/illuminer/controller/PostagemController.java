package com.illuminer.illuminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.illuminer.illuminer.model.Postagem;
import com.illuminer.illuminer.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<Page<Postagem>> listarTodosPaginado(Pageable pageable) {
		return ResponseEntity.ok(postagemRepository.findAll(pageable));
	}
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity<Page<Postagem>> listarPorTextoPaginado(@PathVariable String texto,
			Pageable pageable) {
		return ResponseEntity.ok(postagemRepository.findByTextoContainingIgnoreCase(texto, pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> obterPorId(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(postagem -> ResponseEntity.ok(postagem))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> criar(@RequestBody Postagem postagem) {
		if(postagem.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		postagem = postagemRepository.save(postagem);
		postagem.getTema().setPostagens(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem postagem) {
		if(postagemRepository.existsById(id) == false) {
			return ResponseEntity.notFound().build();
		}
		
		postagem.setId(id);
		
		return ResponseEntity.ok(postagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(postagemRepository.existsById(id) == false) {
			return ResponseEntity.notFound().build();
		}
		
		postagemRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
