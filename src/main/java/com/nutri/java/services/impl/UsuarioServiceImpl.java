package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IUsuarioDAO;
import com.nutri.java.model.Usuario;
import com.nutri.java.services.IUsuarioService;

/**
 * Implementa a interface de serviços da entidade Usuario
 * 
 * @author Milena Santos
 * @version 1.0
 *
 * @see IUsuarioService
 */
public class UsuarioServiceImpl implements IUsuarioService {

	private IUsuarioDAO usuarioDAO;

	public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void delete(Usuario entity) {
		this.usuarioDAO.delete(entity);
	}

	public Usuario get(Serializable id) {
		return this.usuarioDAO.get(id);
	}

	public List<Usuario> listAll() {
		return this.usuarioDAO.listAll();
	}

	public List<Usuario> listAll(int first, int max) {
		return this.usuarioDAO.listAll();
	}

	public Usuario save(Usuario entity) {
		return this.usuarioDAO.save(entity);
	}

	public void update(Usuario entity) {
		this.usuarioDAO.update(entity);
	}

	public Usuario findById(int id) {
		return this.usuarioDAO.findById(id);
	}

	public List<Usuario> findByName(String name) {
		return this.usuarioDAO.findByName(name);
	}

	public boolean validarLogin(String login, String senha) {
		return this.usuarioDAO.validarLogin(login, senha);
	}

	public boolean validarUsuario(Usuario usuario) {
		return this.usuarioDAO.validarUsuario(usuario);
	}
	
	public Usuario findUserByLogin(String login) {
		return this.usuarioDAO.findUserByLogin(login);
	}

	// public List<Usuario> findByExample(Usuario entity) {
	// return this.usuarioDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Usuario entity) {
	// return this.usuarioDAO.findByExamplePageCount(entity);
	// }

	// public Usuario findOneByExample(Usuario entity) {
	// return this.usuarioDAO.findOneByExample(entity);
	// }
	
	// public int listAllPageCount() {
	// return this.usuarioDAO.listAllPageCount();
	// }

	// public List<Usuario> listByExample(Usuario entity, int first,
	// int max) {
	// return this.usuarioDAO.listByExample(entity, first, max);
	// }

	// public Usuario load(Serializable id) {
	// return this.usuarioDAO.load(id);
	// }

	// public void rebind(Usuario entity) {
	// this.usuarioDAO.rebind(entity);
	// }
	
	// public List<Usuario> loadAll() {
	// return this.usuarioDAO.loadAll();
	// }

}
