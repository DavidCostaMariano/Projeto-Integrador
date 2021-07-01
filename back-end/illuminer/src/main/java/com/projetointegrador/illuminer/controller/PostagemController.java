package com.projetointegrador.illuminer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.PostagemDestaqueComentario;
import com.projetointegrador.illuminer.repository.PostagemRepository;
import com.projetointegrador.illuminer.repository.UsuarioRepository;
import com.projetointegrador.illuminer.service.PostagemService;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoPostagem;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> listarTodos() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Postagem>> listarTodos(Pageable pageable) {
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
	
	@GetMapping("/engajamento/comentarios")
	public ResponseEntity<PostagemDestaqueComentario> obterPostagemComentario() {
		return ResponseEntity.ok(postagemService.obterPostagemComMaisComentarios());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> criar(@RequestBody @Valid Postagem postagem) {
		if(usuarioRepository.existsById(postagem.getUsuario().getId()) == false) {
			return ResponseEntity.notFound().build();
		}
		postagem = postagemRepository.save(postagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
	}

	@PutMapping
	public ResponseEntity<Postagem> atualizar(@RequestBody @Validated(ValidationGroupAtualizacaoPostagem.class) Postagem postagem) {
		if(postagemRepository.existsById(postagem.getId()) == false || 
				 usuarioRepository.existsById(postagem.getUsuario().getId()) == false) {
			return ResponseEntity.notFound().build();
		}
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
