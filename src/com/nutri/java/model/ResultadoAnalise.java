package com.nutri.java.model;

import java.util.Date;

/**
 * Encapsula as regras de negocio da entidade ResultadoAnalise
 * 
 * @author Milena Santos
 * @version 1.0
 */
public class ResultadoAnalise {

	private Integer id;
	private Paciente paciente;
	private Date dataInicial;
	private Date dataFinal;
	private Integer posicaoRanking;
	private String informativo;

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

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getPosicaoRanking() {
		return posicaoRanking;
	}

	public void setPosicaoRanking(Integer posicaoRanking) {
		this.posicaoRanking = posicaoRanking;
	}

	public String getInformativo() {
		return informativo;
	}

	public void setInformativo(String informativo) {
		this.informativo = informativo;
	}
}
