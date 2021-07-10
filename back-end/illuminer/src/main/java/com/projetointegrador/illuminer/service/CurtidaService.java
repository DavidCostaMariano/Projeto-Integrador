package com.projetointegrador.illuminer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetointegrador.illuminer.model.Postagem;
import com.projetointegrador.illuminer.model.PostagemDestaqueCurtida;
import com.projetointegrador.illuminer.repository.PostagemRepository;

@Service
public class CurtidaService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	public PostagemDestaqueCurtida obterPostagemComMaisCurtidas() {
		List<Postagem> postagens = postagemRepository.findAll();
		
		int maior = 0;
		Postagem postagemComMaisCurtidas = postagens.get(0);
		
		for(Postagem postagem : postagens) {
			if(postagem.getCurtidas().size() > maior) {
				maior = postagem.getCurtidas().size();
				postagemComMaisCurtidas = postagem;
			}
		}
		
		PostagemDestaqueCurtida postagemDestaqueCurtida = new PostagemDestaqueCurtida();
		postagemDestaqueCurtida.setId(postagemComMaisCurtidas.getId());
		postagemDestaqueCurtida.setQtdCurtidas(postagemComMaisCurtidas.getCurtidas().size());
		postagemDestaqueCurtida.setTitulo(postagemComMaisCurtidas.getTitulo());
		
		return postagemDestaqueCurtida;
	}
}
