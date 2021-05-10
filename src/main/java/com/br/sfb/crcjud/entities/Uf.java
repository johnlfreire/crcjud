package com.br.sfb.crcjud.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="uf")
public class Uf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome_uf;
	private String sigla_uf;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome_uf() {
		return nome_uf;
	}
	public void setNome_uf(String nome_uf) {
		this.nome_uf = nome_uf;
	}
	public String getSigla_uf() {
		return sigla_uf;
	}
	public void setSigla_uf(String sigla_uf) {
		this.sigla_uf = sigla_uf;
	}
	
	
}
