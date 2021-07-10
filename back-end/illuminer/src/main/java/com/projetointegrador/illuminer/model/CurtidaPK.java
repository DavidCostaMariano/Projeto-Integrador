package com.projetointegrador.illuminer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class CurtidaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;
	
	
	@ManyToOne
	@JsonIgnoreProperties({"curtidas", "comentarios" })
	private Postagem postagem;


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Postagem getPostagem() {
		return postagem;
	}


	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
	
	
	

}
