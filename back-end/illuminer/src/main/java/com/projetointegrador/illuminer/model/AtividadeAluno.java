package com.projetointegrador.illuminer.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AtividadeAluno {
	
	private Long idPostagem;
	private Long idAutorPostagem;
	
	private String nomeAutorPostagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	private String texto;
	
	private String textoPost;
	
	private String tipo;
	

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public Long getIdAutorPostagem() {
		return idAutorPostagem;
	}

	public void setIdAutorPostagem(Long idAutorPostagem) {
		this.idAutorPostagem = idAutorPostagem;
	}
	
	public String getNomeAutorPostagem() {
		return nomeAutorPostagem;
	}

	public void setNomeAutorPostagem(String nomeAutorPostagem) {
		this.nomeAutorPostagem = nomeAutorPostagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTextoPost() {
		return textoPost;
	}

	public void setTextoPost(String textoPost) {
		this.textoPost = textoPost;
	}
}
