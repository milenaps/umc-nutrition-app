package com.nutri.java.dao;

import com.nutri.java.commons.IBaseDAO;
import com.nutri.java.model.Usuario;

/**
 * Interface de persistencia para a entidade Usuario
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IBaseDAO
 */
public interface IUsuarioDAO extends IBaseDAO<Usuario> {

	/**
	 * Procura pelo usuario que possui o login informado
	 * 
	 * @param login
	 *            String
	 * @return O usuario
	 */
	public Usuario findUserByLogin(String login);

	/**
	 * Valida os dados do login informado
	 * 
	 * @param login
	 *            String
	 * @param senha
	 *            String
	 * @return Se o login eh valido
	 */
	public boolean validarLogin(String login, String senha);
	
	/**
	 * Valida o usuario informado
	 * 
	 * @param usuario
	 * 			Usuario
	 * @return Se o usuario pode acessar a aplicacao
	 */
	public boolean validarUsuario(Usuario usuario);
}
