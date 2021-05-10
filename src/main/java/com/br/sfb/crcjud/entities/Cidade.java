package com.br.sfb.crcjud.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="cidade")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_municipio;
	@ManyToOne
    @JoinColumn(name = "id_estado")	
    private Uf estados;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome_municipio() {
		return nome_municipio;
	}
	public void setNome_municipio(String nome_municipio) {
		this.nome_municipio = nome_municipio;
	}
	public Uf getEstados() {
		return estados;
	}
	public void setEstados(Uf estados) {
		this.estados = estados;
	}

}
