package com.projetointegrador.illuminer.model;

public class UsuarioDestaque {
	
	private Long id;
	
	private String nome;
	
	private String foto;
	
	private Integer qtdPostagens;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getQtdPostagens() {
		return qtdPostagens;
	}

	public void setQtdPostagens(Integer qtdPostagens) {
		this.qtdPostagens = qtdPostagens;
	}
}
