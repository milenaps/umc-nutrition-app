package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Composicao
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Composicao {

	private Integer id;
	private Nutriente nutriente;
	private Double quantidade;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Nutriente getNutriente() {
		return nutriente;
	}

	public void setNutriente(Nutriente nutriente) {
		this.nutriente = nutriente;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

}
