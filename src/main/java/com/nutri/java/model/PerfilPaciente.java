package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade PerfilPaciente
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class PerfilPaciente {

	private Integer id;
	private String perfil;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}	
}
