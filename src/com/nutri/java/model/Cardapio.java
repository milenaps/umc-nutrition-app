package com.nutri.java.model;

import java.util.List;

/**
 * Encapsula as regras de negocio da entidade Cardapio
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Cardapio {

	private Integer id;
	private Paciente paciente;
	private Nutricionista nutricionista;
	private List<ItemCardapio> itens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public List<ItemCardapio> getItens() {
		return itens;
	}

	public void setItens(List<ItemCardapio> itens) {
		this.itens = itens;
	}

}
