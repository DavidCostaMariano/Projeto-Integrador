package com.projetointegrador.illuminer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoComentario;
import com.projetointegrador.illuminer.validations.ValidationGroupId;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "tb_comentario")
public class Comentario {

	@NotNull(groups = { ValidationGroupAtualizacaoComentario.class, ValidationGroupId.class })
	@Null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(groups = { Default.class, ValidationGroupAtualizacaoComentario.class })
	@Size(min = 2, groups = { Default.class, ValidationGroupAtualizacaoComentario.class })
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@NotNull(groups = { Default.class, ValidationGroupAtualizacaoComentario.class })
	@Valid
	@ConvertGroup(from = Default.class, to =  ValidationGroupId.class )
	@ConvertGroup(from = ValidationGroupAtualizacaoComentario.class, to =  ValidationGroupId.class )
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;
	
	@NotNull(groups = { Default.class, ValidationGroupAtualizacaoComentario.class })
	@Valid
	@ConvertGroup(from = Default.class, to =  ValidationGroupId.class )
	@ConvertGroup(from = ValidationGroupAtualizacaoComentario.class, to =  ValidationGroupId.class )
	@ManyToOne
	@JsonIgnoreProperties("comentarios")
	private Postagem postagem;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
}
