package com.nutri.java.model;

import java.util.Date;
import java.util.List;

/**
 * Encapsula as regras de negocio da entidade Paciente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see Usuario
 */
public class Paciente extends Usuario {

	private Date dataNascto;
	private Double peso;
	private Double altura;
	private Integer sexo;
	private Nutricionista nutricionista;
	private PerfilPaciente perfil;
	private List<HistoricoAlimentar> historicosAlimentares;
	private List<Atividade> atividades;
	private List<Patologia> patologias;
	
	public Date getDataNascto() {
		return dataNascto;
	}

	public void setDataNascto(Date dataNascto) {
		this.dataNascto = dataNascto;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public List<HistoricoAlimentar> getHistoricosAlimentares() {
		return historicosAlimentares;
	}

	public void setHistoricosAlimentares(
			List<HistoricoAlimentar> historicosAlimentares) {
		this.historicosAlimentares = historicosAlimentares;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public PerfilPaciente getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilPaciente perfil) {
		this.perfil = perfil;
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Patologia> getPatologias() {
		return patologias;
	}

	public void setPatologias(List<Patologia> patologias) {
		this.patologias = patologias;
	}

}
