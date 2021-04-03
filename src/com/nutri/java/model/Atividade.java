package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Atividade
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Atividade {

	private Integer id;
	private String nome;
	private FrequenciaAtividade frequencia;

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
	
	public FrequenciaAtividade getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(FrequenciaAtividade frequencia) {
		this.frequencia = frequencia;
	}

}
