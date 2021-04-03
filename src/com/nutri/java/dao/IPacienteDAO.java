package com.nutri.java.dao;

import java.util.Date;
import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.HistoricoAlimentar;
import com.nutri.java.model.Paciente;

/**
 * Interface de persistencia para a entidade Paciente
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IPacienteDAO extends IBaseDAO<Paciente> {

	/**
	 * Encontra o paciente dado o seu id de usuario
	 * 
	 * @param usuario
	 *            int
	 * @return O paciente
	 */
	public Paciente findByUserId(int usuario);

	/**
	 * Lista os historicos alimentares do paciente
	 * 
	 * @param paciente
	 * 			int
	 * @return Os historicos
	 */
	public List<HistoricoAlimentar> listHistoricosAlimentares(int paciente);
	
	/**
	 * Lista os historicos alimentares por periodo, dados o id do paciente, a
	 * data inicial e a data final
	 * 
	 * @param paciente
	 *            int
	 * @param dataDe
	 *            Date
	 * @param dataAte
	 *            Date
	 * @return A lista
	 */
	public List<HistoricoAlimentar> listHistoricosByPeriod(int paciente,
			Date dataDe, Date dataAte);
}
