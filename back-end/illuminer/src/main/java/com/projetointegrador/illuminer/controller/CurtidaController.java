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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegrador.illuminer.model.Curtida;
import com.projetointegrador.illuminer.model.CurtidaPK;
import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.Usuario;
import com.projetointegrador.illuminer.repository.CurtidaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping ("/curtidas")
@CrossOrigin ("*")
public class CurtidaController {
	
	@Autowired
	private CurtidaRepository repository;
	
	/*@PostMapping
	public ResponseEntity<Curtida> post (@RequestBody @Valid CurtidaPK curtidas) {
		System.out.println("TA CHAMANDO");
		System.out.println(curtidas);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(new Curtida(curtidas)));
	}*/
	
	@PostMapping
	public ResponseEntity<Curtida> post (@RequestParam(name = "usuario", required = true) Long idUsuario,
			@RequestParam(name = "postagem", required = true) Long idPostagem) {
		System.out.println("TA CHAMANDO");
		CurtidaPK id = new CurtidaPK(new Usuario(idUsuario), new Postagem(idPostagem));
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(new Curtida(id)));
	}
	
	
	@DeleteMapping
	public ResponseEntity<Void> delete (@RequestParam(name = "usuario", required = true) Long idUsuario,
			@RequestParam(name = "postagem", required = true) Long idPostagem){
		System.out.println("TA CHAMANDO DELETE");
		CurtidaPK id = new CurtidaPK(new Usuario(idUsuario), new Postagem(idPostagem));
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
