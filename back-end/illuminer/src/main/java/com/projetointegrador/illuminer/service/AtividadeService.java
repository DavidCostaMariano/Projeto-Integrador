package com.projetointegrador.illuminer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetointegrador.illuminer.model.AtividadeAluno;
import com.projetointegrador.illuminer.model.Comentario;
import com.projetointegrador.illuminer.model.Curtida;
import com.projetointegrador.illuminer.repository.ComentarioRepository;
import com.projetointegrador.illuminer.repository.CurtidaRepository;

@Service
public class AtividadeService {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private CurtidaRepository curtidaRepository;
	
	public Page<AtividadeAluno> obterAtividadesAluno(Long idAluno, Pageable pageable) {
		List<Comentario> comentarios = comentarioRepository.listarComentariosDeUmUsuario(idAluno);
		List<Curtida> curtidas =  curtidaRepository.obterCurtidasDeUmUsuario(idAluno);
		List<AtividadeAluno> atividadesAluno = new ArrayList<>();
		
		comentarios.forEach(comentario -> {
			AtividadeAluno atividadeAluno = new AtividadeAluno();
			atividadeAluno.setIdPostagem(comentario.getPostagem().getId());
			atividadeAluno.setIdAutorPostagem(comentario.getPostagem().getUsuario().getId());
			atividadeAluno.setNomeAutorPostagem(comentario.getPostagem().getUsuario().getNome());
			atividadeAluno.setData(comentario.getData());
			atividadeAluno.setTexto(comentario.getTexto().length() > 50 ? comentario.getTexto().substring(0, 50) : comentario.getTexto());
			atividadeAluno.setTipo("comentario");
			atividadesAluno.add(atividadeAluno);
		});
		
		curtidas.forEach(curtida -> {
			AtividadeAluno atividadeAluno = new AtividadeAluno();
			atividadeAluno.setIdPostagem(curtida.getId().getPostagem().getId());
			atividadeAluno.setIdAutorPostagem(curtida.getId().getPostagem().getUsuario().getId());
			atividadeAluno.setNomeAutorPostagem(curtida.getId().getPostagem().getUsuario().getNome());
			atividadeAluno.setData(curtida.getData());
			atividadeAluno.setTexto(curtida.getId().getPostagem().getTitulo());
			atividadeAluno.setTipo("curtida");
			atividadesAluno.add(atividadeAluno);
		});
		
		List<AtividadeAluno> atividadesAlunoOrdenado = atividadesAluno
				.stream().sorted((a1, a2) -> -a1.getData().compareTo(a2.getData())).collect(Collectors.toList());
		PagedListHolder<AtividadeAluno> page = new PagedListHolder<>(atividadesAlunoOrdenado);
		page.setPageSize(pageable.getPageSize());
		page.setPage(pageable.getPageNumber());
		
		Page<AtividadeAluno> atividadesAlunoPagina = new PageImpl<>(page.getPageList(), pageable, atividadesAlunoOrdenado.size());
		
		return atividadesAlunoPagina;
	}
}
