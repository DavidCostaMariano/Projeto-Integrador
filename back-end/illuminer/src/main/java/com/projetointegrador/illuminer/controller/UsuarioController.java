package com.projetointegrador.illuminer.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.projetointegrador.illuminer.model.AtividadeAluno;
import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.Usuario;
import com.projetointegrador.illuminer.model.UsuarioDestaque;
import com.projetointegrador.illuminer.model.UsuarioLogin;
import com.projetointegrador.illuminer.repository.PostagemRepository;
import com.projetointegrador.illuminer.repository.UsuarioRepository;
import com.projetointegrador.illuminer.service.AtividadeService;
import com.projetointegrador.illuminer.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private AtividadeService atividadeService;
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		return usuarioRepository.findById(id).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}

	
	@GetMapping("/{id}/postagens")
	public ResponseEntity<Page<Postagem>> listarPostagens(@PathVariable(name = "id") Long idUsuario, Pageable pageable) {
		System.out.println(idUsuario);
		System.out.println(pageable);
		Page<Postagem> postagens = postagemRepository.listarPostagensPorUsuario(idUsuario, pageable);
		System.out.println(postagens.getContent());
		return ResponseEntity.ok(postagens);
	}
	
	@GetMapping("/engajamento/postagens")
	public ResponseEntity<UsuarioDestaque> obterUsuarioComMaisPostagem() {
		return ResponseEntity.ok(usuarioService.obterUsuarioComMaisPostagens());
	}
	

	/*@GetMapping("/{id}/curtida")
	public ResponseEntity<Curtida> obterCurtidasUsuario() {
		return ResponseEntity.ok(curtidaService.obterCurtidasUsuario());
	}*/

	@GetMapping("/{id}/atividades")
	public ResponseEntity<Page<AtividadeAluno>> obterAtividadesAluno(@PathVariable(name = "id") Long idAluno, Pageable pageable) {
		if(!usuarioRepository.existsById(idAluno)) {
			return ResponseEntity.notFound().build();
		} 
		
		Usuario usuario = usuarioRepository.findById(idAluno).get();
		if(usuario.getTipo().equalsIgnoreCase("aluno")) {
			return ResponseEntity.ok(atividadeService.obterAtividadesAluno(idAluno, pageable));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario){
		try {
			usuario = usuarioService.cadastrar(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		} catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> logar(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return usuarioService.logar(usuarioLogin)
				.map(login -> ResponseEntity.ok(login))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		if(usuarioRepository.existsById(usuario.getId()) == false) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.setPostagens(null);
		
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}
  
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(usuarioRepository.existsById(id) == false) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
