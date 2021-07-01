package com.projetointegrador.illuminer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.PostagemDestaqueComentario;
import com.projetointegrador.illuminer.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	
	public PostagemDestaqueComentario obterPostagemComMaisComentarios() {
		List<Postagem> postagens = postagemRepository.findAll();
		int maior = 0;
		Postagem postagemComMaisComentarios = postagens.get(0);
		for(Postagem postagem : postagens) {
			if(postagem.getComentarios().size() > maior) {
				maior = postagem.getComentarios().size();
				postagemComMaisComentarios = postagem;
			}
		}
		PostagemDestaqueComentario postagemDestaqueComentario = new PostagemDestaqueComentario();
		postagemDestaqueComentario.setId(postagemComMaisComentarios.getId());
		postagemDestaqueComentario.setQtdComentarios(postagemComMaisComentarios.getComentarios().size());
		postagemDestaqueComentario.setTitulo(postagemComMaisComentarios.getTitulo());
		return postagemDestaqueComentario;
	}

}
