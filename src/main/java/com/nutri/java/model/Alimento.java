package com.nutri.java.model;

import java.util.List;

/**
 * Encapsula as regras de negocio da entidade Alimento
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Alimento {

	private Integer id;
	private String nome;
	private Medida medida;
	private Categoria categoria;
	private List<Composicao> nutrientes;

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
	
	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Composicao> getNutrientes() {
		return nutrientes;
	}

	public void setNutrientes(List<Composicao> nutrientes) {
		this.nutrientes = nutrientes;
	}

}
