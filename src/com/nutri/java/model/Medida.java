package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Medida
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Medida {

	private Integer id;
	private String nome;
	private Double quantidade;

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
	
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
