package com.br.sfb.crcjud.entities;

import java.io.Serializable;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vara")
public class Vara implements Serializable{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Nome é Obrigatorio")
	private String nome;
	@NotNull(message = "É necessario informar a comarca")
	@ManyToOne
	private Cidade cidade;
	@NotNull(message = "É necessario informar a entidade")
	@ManyToOne
	private Entidade entidade;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cidade getCidade() {
		return cidade;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Entidade getEntidade() {
		return entidade;
	}
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	

}
