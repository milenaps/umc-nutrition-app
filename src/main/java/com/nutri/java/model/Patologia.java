package com.nutri.java.model;

import java.util.List;

/**
 * Encapsula as regras de negocio da entidade Patologia
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class Patologia {

	private Integer id;
	private String nome;
	private String descricao;
	private List<Alimento> restrAlimentos;
	private List<Categoria> restrCategorias;
	private List<GrupoAlimentar> restrGrupos;
	private List<Nutriente> restrNutrientes;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Alimento> getRestrAlimentos() {
		return restrAlimentos;
	}

	public void setRestrAlimentos(List<Alimento> restrAlimentos) {
		this.restrAlimentos = restrAlimentos;
	}

	public List<Categoria> getRestrCategorias() {
		return restrCategorias;
	}

	public void setRestrCategorias(List<Categoria> restrCategorias) {
		this.restrCategorias = restrCategorias;
	}

	public List<GrupoAlimentar> getRestrGrupos() {
		return restrGrupos;
	}

	public void setRestrGrupos(List<GrupoAlimentar> restrGrupos) {
		this.restrGrupos = restrGrupos;
	}

	public List<Nutriente> getRestrNutrientes() {
		return restrNutrientes;
	}

	public void setRestrNutrientes(List<Nutriente> restrNutrientes) {
		this.restrNutrientes = restrNutrientes;
	}

}
