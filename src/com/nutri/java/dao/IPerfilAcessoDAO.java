package com.nutri.java.dao;

import java.util.List;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.PerfilAcesso;

/**
 * Interface de persistencia para a entidade PerfilAcesso
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IPerfilAcessoDAO extends IBaseDAO<PerfilAcesso> {

	/**
	 * Lista os perfis de acesso dado o login
	 * 
	 * @param login
	 *            int
	 * @return A lista
	 */
	public List<PerfilAcesso> listPerfisPorLogin(int login);
}
