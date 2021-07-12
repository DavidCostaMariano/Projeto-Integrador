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
	
	public CurtidaPK() {}

	public CurtidaPK(Usuario usuario, Postagem postagem) {
		this.usuario = usuario;
		this.postagem = postagem;
	}
	
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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postagem == null) ? 0 : postagem.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurtidaPK other = (CurtidaPK) obj;
		if (postagem == null) {
			if (other.postagem != null)
				return false;
		} else if (!postagem.equals(other.postagem))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurtidaPK [usuario=" + usuario + ", postagem=" + postagem + "]";
	}
}
