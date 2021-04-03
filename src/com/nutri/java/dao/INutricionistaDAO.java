package com.nutri.java.dao;

import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.Nutricionista;

/**
 * Interface de persistencia para a entidade Nutricionista
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface INutricionistaDAO extends IBaseDAO<Nutricionista> {

	/**
	 * Encontra o nutricionista dado o seu id de usuario
	 * 
	 * @param usuario
	 *            int
	 * @return O nutricionista
	 */
	public Nutricionista findByUserId(int usuario);
	
	/**
	 * Lista nutricionista que necessitam aprovacao do administrador
	 * 
	 * @return A lista
	 */
	public List<Nutricionista> listForApproval();
}
