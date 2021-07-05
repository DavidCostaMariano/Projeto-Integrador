package com.projetointegrador.illuminer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoPostagem;
import com.projetointegrador.illuminer.validations.ValidationGroupId;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "tb_postagem")
public class Postagem {

	@NotNull(groups = { ValidationGroupAtualizacaoPostagem.class, ValidationGroupId.class })
	@Null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Null(groups = { Default.class, ValidationGroupAtualizacaoPostagem.class })
	private String titulo;
	
	@NotBlank(groups = { Default.class, ValidationGroupAtualizacaoPostagem.class })
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	private String midia;
	
	private String tipoMidia;
	
	
	@OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("postagem")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroupId.class)
	@ConvertGroup(from = ValidationGroupAtualizacaoPostagem.class, to = ValidationGroupId.class)
	@NotNull(groups = { Default.class, ValidationGroupAtualizacaoPostagem.class })
	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Usuario usuario;

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

	public String getMidia() {
		return midia;
	}

	public void setMidia(String midia) {
		this.midia = midia;
	}

	public String getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(String tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentario) {
		this.comentarios = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void tratarTitulo() {
		if(texto.length() > 50) {
			titulo = texto.substring(0, 50);
		} else {
			titulo = texto;
		}
	}
	
	public void tratarLinkVideo() {
		if((!tipoMidia.isBlank() || tipoMidia != null) && tipoMidia.equalsIgnoreCase("video")) {
			String[] link = midia.split("=");
			String linkValido = link[1].split("&")[0];
			midia = String.format("https://www.youtube.com/embed/%s", linkValido);
		}
	}	
}
