package com.nutri.java.dao;

import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.Alimento;

/**
 * Interface de persistencia para a entidade Alimento
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IAlimentoDAO extends IBaseDAO<Alimento> {

	/**
	 * Lista alimentos dada a categoria informada
	 * 
	 * @param categ
	 *            int
	 * @return A lista
	 */
	public List<Alimento> listPorCateg(int categ);
}