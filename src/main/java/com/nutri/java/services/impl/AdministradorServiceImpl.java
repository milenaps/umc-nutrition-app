package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IAdministradorDAO;
import com.nutri.java.model.Administrador;
import com.nutri.java.services.IAdministradorService;

/**
 * Implementa a interface de serviços da entidade Administrador
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IAdministradorService
 */
public class AdministradorServiceImpl implements IAdministradorService {

	private IAdministradorDAO administradorDAO;

	public void setAdministradorDAO(IAdministradorDAO administradorDAO) {
		this.administradorDAO = administradorDAO;
	}
	
	public Administrador get(Serializable id) {
		return this.administradorDAO.get(id);
	}

	public void delete(Administrador entity) {
		this.administradorDAO.delete(entity);
	}

	public List<Administrador> listAll() {
		return this.administradorDAO.listAll();
	}

	public List<Administrador> listAll(int first, int max) {
		return this.administradorDAO.listAll();
	}

	public Administrador save(Administrador entity) {
		return this.administradorDAO.save(entity);
	}

	public void update(Administrador entity) {
		this.administradorDAO.update(entity);
	}
	
	public Administrador findById(int id) {
		return this.administradorDAO.findById(id);
	}
	
	public List<Administrador> findByName(String name) {
		return this.administradorDAO.findByName(name);
	}

	public Administrador findByUserId(int usuario) {
		return this.administradorDAO.findByUserId(usuario);
	}

	public void aprovarNutricionista(int nutricionista) {
	    this.administradorDAO.aprovarNutricionista(nutricionista);
	}
}
