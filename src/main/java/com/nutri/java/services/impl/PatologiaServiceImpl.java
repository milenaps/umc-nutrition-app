package com.nutri.java.services.impl;

import java.io.Serializable;
import java.util.List;

import com.nutri.java.dao.IPatologiaDAO;
import com.nutri.java.model.Patologia;
import com.nutri.java.services.IPatologiaService;

/**
 * Implementa a interface de serviços da entidade Patologia
 * 
 * @author Milena Santos
 * @version 1.0
 * 
 * @see IPatologiaService
 */
public class PatologiaServiceImpl implements IPatologiaService {

	private IPatologiaDAO patologiaDAO;

	public void setPatologiaDAO(IPatologiaDAO patologiaDAO) {
		this.patologiaDAO = patologiaDAO;
	}

	public void delete(Patologia entity) {
		this.patologiaDAO.delete(entity);
	}

	public Patologia get(Serializable id) {
		return this.patologiaDAO.get(id);
	}

	public List<Patologia> listAll() {
		return this.patologiaDAO.listAll();
	}

	public List<Patologia> listAll(int first, int max) {
		return this.patologiaDAO.listAll();
	}

	public Patologia save(Patologia entity) {
		return this.patologiaDAO.save(entity);
	}

	public void update(Patologia entity) {
		this.patologiaDAO.update(entity);
	}

	public Patologia findById(int id) {
		return this.patologiaDAO.findById(id);
	}

	public List<Patologia> findByName(String name) {
		return this.patologiaDAO.findByName(name);
	}

	// public List<Patologia> findByExample(Patologia entity) {
	// return this.patologiaDAO.findByExample(entity);
	// }

	// public int findByExamplePageCount(Patologia entity) {
	// return this.patologiaDAO.findByExamplePageCount(entity);
	// }

	// public Patologia findOneByExample(Patologia entity) {
	// return this.patologiaDAO.findOneByExample(entity);
	// }

	// public int listAllPageCount() {
	// return this.patologiaDAO.listAllPageCount();
	// }

	// public List<Patologia> listByExample(Patologia entity, int first,
	// int max) {
	// return this.patologiaDAO.listByExample(entity, first, max);
	// }

	// public Patologia load(Serializable id) {
	// return this.patologiaDAO.load(id);
	// }

	// public void rebind(Patologia entity) {
	// this.patologiaDAO.rebind(entity);
	// }

	// public List<Patologia> loadAll() {
	// return this.patologiaDAO.loadAll();
	// }

}
