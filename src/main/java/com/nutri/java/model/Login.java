package com.nutri.java.model;

import java.util.List;

/**
 * Encapsula as regras de negocio da entidade Login
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Login {

	private Integer id;
	private String login;
	private String senha;
	private List<PerfilAcesso> perfis;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PerfilAcesso> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<PerfilAcesso> perfis) {
		this.perfis = perfis;
	}
}
