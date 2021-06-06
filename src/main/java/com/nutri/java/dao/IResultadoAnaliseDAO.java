package com.nutri.java.dao;

import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.ResultadoAnalise;

/**
 * Interface de persistencia para a entidade ResultadoAnalise
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IResultadoAnaliseDAO extends IBaseDAO<ResultadoAnalise> {

	/**
	 * Lista resultados de análises dado o id do paciente
	 * 
	 * @param paciente
	 *            int
	 * @return A lista
	 */
	public List<ResultadoAnalise> listByPaciente(int paciente);
}
