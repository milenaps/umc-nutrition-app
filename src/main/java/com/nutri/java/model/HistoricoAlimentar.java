package com.nutri.java.model;

import java.util.Date;

/**
 * Encapsula as regras de negocio da entidade HistoricoAlimentar
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class HistoricoAlimentar {

	private Integer id;
	private Date data;
	private String hora;
	private Alimento alimento;
	private Double quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
