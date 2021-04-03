package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Nutricionista
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see Usuario
 */
public class Nutricionista extends Usuario {

	private String crn;
	private Boolean aprovado;

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public Boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
}
