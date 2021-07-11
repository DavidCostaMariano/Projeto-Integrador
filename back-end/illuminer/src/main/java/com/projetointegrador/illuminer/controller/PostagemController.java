package com.projetointegrador.illuminer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.PostagemDestaqueComentario;
import com.projetointegrador.illuminer.model.PostagemDestaqueCurtida;
import com.projetointegrador.illuminer.repository.ComentarioRepository;
import com.projetointegrador.illuminer.repository.PostagemRepository;
import com.projetointegrador.illuminer.repository.UsuarioRepository;
import com.projetointegrador.illuminer.service.CurtidaService;
import com.projetointegrador.illuminer.service.PostagemService;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoPostagem;

@RestController
@CrossOrigin("*")
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private CurtidaService curtidaService;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Postagem>> listarTodos() {
		List<Postagem> postagens = postagemRepository.findAll();
		postagens.forEach(postagem -> postagem.ordernarComentarios());
		return ResponseEntity.ok(postagens);
	}
	
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Postagem>> listarTodos(Pageable pageable) {
		Page<Postagem> page = postagemRepository.findAll(pageable);
		page.getContent().forEach(postagem -> postagem.ordernarComentarios());
		return ResponseEntity.ok(page);
	}
	@GetMapping("/pagina/{id}")
	public ResponseEntity<Page<Postagem>> listarPorEngajamento(@PathVariable Long id, 
			Pageable pageable) {
		Page<Postagem> page = postagemRepository.findById(id, pageable);
		page.getContent().forEach(postagem -> postagem.ordernarComentarios());
		return ResponseEntity.ok(page);
	}
		
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity<Page<Postagem>> listarPorTextoPaginado(@PathVariable String texto,
			Pageable pageable) {
		Page<Postagem> page = postagemRepository.findByTextoContainingIgnoreCase(texto, pageable);
		page.getContent().forEach(postagem -> postagem.ordernarComentarios());
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> obterPorId(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(postagem -> {
					postagem.ordernarComentarios();
					return ResponseEntity.ok(postagem);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/engajamento/comentarios")
	public ResponseEntity<PostagemDestaqueComentario> obterPostagemComentario() {
		return ResponseEntity.ok(postagemService.obterPostagemComMaisComentarios());
	}
	
	@GetMapping("/engajamento/curtidas")
	public ResponseEntity<PostagemDestaqueCurtida> obterPostagemCurtida() {
		return ResponseEntity.ok(curtidaService.obterPostagemComMaisCurtidas());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> criar(@RequestBody @Valid Postagem postagem) {
		if(usuarioRepository.existsById(postagem.getUsuario().getId()) == false) {
			return ResponseEntity.notFound().build();
		}
		postagem.tratarTitulo();
		postagem.tratarLinkVideo();
		postagem = postagemRepository.save(postagem);
		postagem.ordernarComentarios();
		return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
	}

	@PutMapping
	public ResponseEntity<Postagem> atualizar(@RequestBody @Validated(ValidationGroupAtualizacaoPostagem.class) Postagem postagem) {
		if(postagemRepository.existsById(postagem.getId()) == false || 
				 usuarioRepository.existsById(postagem.getUsuario().getId()) == false) {
			return ResponseEntity.notFound().build();
		}
		postagem.tratarTitulo();
		postagem.tratarLinkVideo();
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
	
	@GetMapping("/{id}/comentarios")
	public ResponseEntity<List<Comentario>> listaComentario(@PathVariable Long id) {
		return ResponseEntity.ok(comentarioRepository.listarComentariosPorPostagem(id));
	}
	
	@GetMapping("/{id}/comentarios/paginado")
	public ResponseEntity<Page<Comentario>> paginacaoComentario(@PathVariable Long id, Pageable pageable){
		return ResponseEntity.ok(comentarioRepository.paginarComentariosPorPostagem(id, pageable));
	}
}
