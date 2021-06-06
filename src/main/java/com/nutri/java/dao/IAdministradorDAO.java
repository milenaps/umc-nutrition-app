package com.nutri.java.dao;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.Administrador;

/**
 * Interface de persistencia para a entidade Administrador
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IAdministradorDAO extends IBaseDAO<Administrador> {

	/**
	 * Encontra o administrador dado o seu id de usuario
	 * 
	 * @param usuario
	 *            int
	 * @return O administrador
	 */
	public Administrador findByUserId(int usuario);
	
	/**
	 * Aprova o nutricionista dado seu id
	 * 
	 * @param id
	 *            int
	 */
	public void aprovarNutricionista(int nutricionista);
}
