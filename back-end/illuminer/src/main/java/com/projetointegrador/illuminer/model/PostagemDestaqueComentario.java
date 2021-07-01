package com.projetointegrador.illuminer.model;

public class PostagemDestaqueComentario {
	
	private Long id;
	
	private String titulo;
	
	private Integer qtdComentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getQtdComentarios() {
		return qtdComentarios;
	}

	public void setQtdComentarios(Integer qtdComentarios) {
		this.qtdComentarios = qtdComentarios;
	}

}
