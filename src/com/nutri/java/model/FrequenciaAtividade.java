package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade FrequenciaAtividade
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class FrequenciaAtividade {

	private Integer id;
	private String frequenciaDiaria;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrequenciaDiaria() {
		return frequenciaDiaria;
	}

	public void setFrequenciaDiaria(String frequenciaDiaria) {
		this.frequenciaDiaria = frequenciaDiaria;
	}
}
