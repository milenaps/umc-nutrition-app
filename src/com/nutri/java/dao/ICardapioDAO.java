package com.nutri.java.dao;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.Cardapio;

public interface ICardapioDAO extends IBaseDAO<Cardapio> {

	/**
	 * Procura um cardápio por id de nutricionista
	 * 
	 * @param nutricionista
	 * 				int
	 * @return O cardapio
	 */
	public Cardapio findByNutricionista(int nutricionista);
	
	/**
	 * Procura um cardápio por id de paciente
	 * 
	 * @param paciente
	 * 				int
	 * @return O cardapio
	 */
	public Cardapio findByPaciente(int paciente);
}
