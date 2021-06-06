package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Nutriente
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Nutriente {

	private Integer id;
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
