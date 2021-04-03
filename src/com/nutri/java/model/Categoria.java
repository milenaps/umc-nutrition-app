package com.nutri.java.model;

/**
 * Encapsula as regras de negocio da entidade Categoria
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Categoria {

	private Integer id;
	private String nome;
	private GrupoAlimentar grupoAlimentar;

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
	
	public GrupoAlimentar getGrupoAlimentar() {
		return grupoAlimentar;
	}

	public void setGrupoAlimentar(GrupoAlimentar grupoAlimentar) {
		this.grupoAlimentar = grupoAlimentar;
	}
	
}
