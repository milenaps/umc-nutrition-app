package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade PerfilAcesso
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class PerfilAcesso {

	private Integer id;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
