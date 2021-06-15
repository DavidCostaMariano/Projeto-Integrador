package com.projetointegrador.illuminer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.projetointegrador.illuminer.validations.ValidationGroupAtualizacaoTema;
import com.projetointegrador.illuminer.validations.ValidationGroupId;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "tb_tema")
public class Tema {

	@NotNull(groups = { ValidationGroupAtualizacaoTema.class, ValidationGroupId.class })
	@Null
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(groups = { Default.class, ValidationGroupAtualizacaoTema.class })
	@Size(min = 2 , max = 50, groups = { Default.class, ValidationGroupAtualizacaoTema.class })
	private String nome;
	
	@JsonIgnoreProperties("tema")
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	private List<Postagem> postagens = new ArrayList<>();
	
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

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}	
}
